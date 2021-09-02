package net.roxia.scheduler.spi;

import net.roxia.scheduler.common.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 参考lts中的spi实现机制
 *
 * @author Robert HG (254963746@qq.com)on 12/23/15.
 */
public class ServiceLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceLoader.class);
    private static final String LTS_DIRECTORY = "META-INF/roxia/";
    private static final String LTS_INTERNAL = "internal";
    private static final String LTS_INTERNAL_DIRECTORY = LTS_DIRECTORY + LTS_INTERNAL + "/";

    private static final ConcurrentMap<Class<?>, ServiceProvider> serviceMap = new ConcurrentHashMap<>();
    private static final ConcurrentMap<ServiceDefinition, Object> cachedObjectMap = new ConcurrentHashMap<>();

    public static <T> T load(Class<T> clazz, String dynamicName) {
        ServiceProvider serviceProvider = getServiceProvider(clazz, dynamicName);
        return load(serviceProvider, dynamicName);
    }

    private static <T> T load(ServiceProvider serviceProvider, String dynamicName) {
        try {
            if (StringUtils.isEmpty(dynamicName)) {
                // 加载默认的
                dynamicName = serviceProvider.defaultName;
            }
            ServiceDefinition definition = serviceProvider.nameMaps.get(dynamicName);
            if (definition == null) {
                throw new IllegalStateException("Serviceloader could not load name:"
                        + dynamicName + "'s ServiceProvider from '" + LTS_DIRECTORY + "' or '"
                        + LTS_INTERNAL_DIRECTORY + "' It may be empty or does not exist.");
            }

            Object obj = cachedObjectMap.get(definition);
            if (obj != null) {
                return (T) obj;
            }
            String className = definition.clazz;
            ClassLoader classLoader = definition.classLoader;
            T srv = ClassLoaderUtil.newInstance(classLoader, className);
            cachedObjectMap.putIfAbsent(definition, srv);
            return srv;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Service loader could not load name:"
                    + dynamicName + "'s ServiceProvider from '" + LTS_DIRECTORY + "' or '"
                    + LTS_INTERNAL_DIRECTORY + "' It may be empty or does not exist.");
        }
    }

    private static ServiceProvider getServiceProvider(Class<?> clazz, String serviceName) {
        ServiceProvider serviceProvider = serviceMap.get(clazz);
        if (serviceProvider == null) {
            getServiceProviders(clazz, serviceName);
            serviceProvider = serviceMap.get(clazz);
        }
        return serviceProvider;
    }

    private static void getServiceProviders(final Class<?> clazz, String serviceName) {

        if (clazz == null)
            throw new IllegalArgumentException("type == null");
        if (!clazz.isInterface()) {
            throw new IllegalArgumentException(" type(" + clazz + ") is not interface!");
        }
        if (!clazz.isAnnotationPresent(SPI.class)) {
            throw new IllegalArgumentException("type(" + clazz +
                    ") is not extension, because WITHOUT @" + SPI.class.getSimpleName() + " Annotation!");
        }

        SPI spi = clazz.getAnnotation(SPI.class);
        String dynamicKey = spi.dynamicKey();

        final Set<URLDefinition> urlDefinitions = new HashSet<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        urlDefinitions.addAll(collectExtensionUrls(LTS_DIRECTORY + clazz.getName(), classLoader));
        urlDefinitions.addAll(collectExtensionUrls(LTS_INTERNAL_DIRECTORY + clazz.getName(), classLoader));

        final ConcurrentMap<String, ServiceDefinition> serviceDefinitions = new ConcurrentHashMap<>();
        for (URLDefinition urlDefinition : urlDefinitions) {
            serviceDefinitions.putAll(parse(urlDefinition));
        }
        if (serviceDefinitions.isEmpty()) {
            throw new IllegalStateException("Service loader could not load " + clazz.getName() + "'s ServiceProvider from '" + LTS_DIRECTORY + "' or '" + LTS_INTERNAL_DIRECTORY + "' It may be empty or does not exist.");
        }
        if (StringUtils.isBlank(serviceName)) {
            serviceName = spi.defaultName();
        }
        ServiceProvider serviceProvider = new ServiceProvider(serviceName, dynamicKey, serviceDefinitions);
        serviceMap.remove(clazz);   // 先移除
        serviceMap.put(clazz, serviceProvider);
    }

    private static Map<String, ServiceDefinition> parse(URLDefinition urlDefinition) {
        final Map<String, ServiceDefinition> nameClassMap = new HashMap<String, ServiceDefinition>();
        try {
            BufferedReader r = null;
            try {
                URL url = urlDefinition.uri.toURL();
                r = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
                while (true) {
                    String line = r.readLine();
                    if (line == null) {
                        break;
                    }
                    int comment = line.indexOf('#');
                    if (comment >= 0) {
                        line = line.substring(0, comment);
                    }
                    line = line.trim();
                    if (line.length() == 0) {
                        continue;
                    }

                    int i = line.indexOf('=');
                    if (i > 0) {
                        String name = line.substring(0, i).trim();
                        String clazz = line.substring(i + 1).trim();
                        nameClassMap.put(name, new ServiceDefinition(name, clazz, urlDefinition.classLoader));
                    }
                }
            } finally {
                if (r != null) {
                    r.close();
                }
            }
        } catch (Exception e) {
            LOGGER.error("parse " + urlDefinition.uri + " error:" + e.getMessage(), e);
        }
        return nameClassMap;
    }

    private static Set<URLDefinition> collectExtensionUrls(String resourceName, ClassLoader classLoader) {
        try {
            final Enumeration<URL> configs;
            if (classLoader != null) {
                configs = classLoader.getResources(resourceName);
            } else {
                configs = ClassLoader.getSystemResources(resourceName);
            }

            Set<URLDefinition> urlDefinitions = new HashSet<>();
            while (configs.hasMoreElements()) {
                URL url = configs.nextElement();
                final URI uri = url.toURI();

                ClassLoader highestClassLoader = findHighestReachableClassLoader(url, classLoader, resourceName);
                urlDefinitions.add(new URLDefinition(uri, highestClassLoader));
            }
            return urlDefinitions;

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return Collections.emptySet();
    }

    private static ClassLoader findHighestReachableClassLoader(URL url, ClassLoader classLoader, String resourceName) {
        if (classLoader.getParent() == null) {
            return classLoader;
        }

        ClassLoader highestClassLoader = classLoader;

        ClassLoader current = classLoader;
        while (current.getParent() != null) {

            ClassLoader parent = current.getParent();

            try {
                Enumeration<URL> resources = parent.getResources(resourceName);
                if (resources != null) {
                    while (resources.hasMoreElements()) {
                        URL resourceURL = resources.nextElement();
                        if (url.toURI().equals(resourceURL.toURI())) {
                            highestClassLoader = parent;
                        }
                    }
                }
            } catch (IOException ignore) {
            } catch (URISyntaxException ignore) {
            }

            current = current.getParent();
        }
        return highestClassLoader;
    }

    private static final class URLDefinition {

        private final URI uri;
        private final ClassLoader classLoader;

        private URLDefinition(URI url, ClassLoader classLoader) {
            this.uri = url;
            this.classLoader = classLoader;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            URLDefinition that = (URLDefinition) o;
            return uri != null ? uri.equals(that.uri) : that.uri == null;
        }

        @Override
        public int hashCode() {
            return uri != null ? uri.hashCode() : 0;
        }
    }

    private static final class ServiceDefinition {

        private final String name;
        private final String clazz;
        private final ClassLoader classLoader;

        private ServiceDefinition(String name, String clazz, ClassLoader classLoader) {
            AssertUtil.notNull(name, "name");
            AssertUtil.notNull(clazz, "clazz");
            AssertUtil.notNull(classLoader, "classLoader");
            this.name = name;
            this.clazz = clazz;
            this.classLoader = classLoader;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ServiceDefinition that = (ServiceDefinition) o;

            if (!Objects.equals(name, that.name)) return false;
            if (!Objects.equals(clazz, that.clazz)) return false;
            return Objects.equals(classLoader, that.classLoader);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (clazz != null ? clazz.hashCode() : 0);
            result = 31 * result + (classLoader != null ? classLoader.hashCode() : 0);
            return result;
        }

    }

    private static final class ServiceProvider {
        private final String dynamicKey;
        private final String defaultName;
        private final ConcurrentMap<String, ServiceDefinition> nameMaps;

        ServiceProvider(String defaultName, String dynamicKey, ConcurrentMap<String, ServiceDefinition> nameMaps) {
            this.dynamicKey = dynamicKey;
            this.defaultName = defaultName;
            this.nameMaps = nameMaps;
        }
    }
}
