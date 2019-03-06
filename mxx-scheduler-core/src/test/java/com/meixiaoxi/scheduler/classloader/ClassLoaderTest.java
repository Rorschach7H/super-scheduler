package com.meixiaoxi.scheduler.classloader;

import junit.framework.TestCase;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: ClassLoaderTest
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-03-04 15:11:42
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-03-04    meixiaoxi       v1.0.0           创建
 */
public class ClassLoaderTest extends TestCase {

    public void testClassLoader() throws IOException, URISyntaxException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> enumeration = classLoader.getResources("META-INF/mxxt/com.meixiaoxi.task.classloader.spi.TestService");
        while (enumeration.hasMoreElements()) {
            URL url = enumeration.nextElement();
            final URI uri = url.toURI();
            System.out.println(uri.getPath());
        }
    }
}
