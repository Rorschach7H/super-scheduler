package com.meixiaoxi.scheduler.store.jdbc.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.meixiaoxi.scheduler.common.PrimitiveTypeUtil;
import com.meixiaoxi.scheduler.core.Config;
import com.meixiaoxi.scheduler.core.constant.ExtConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * druid相关的配置使用 druid. 开头即可
 *
 * @author Robert HG (254963746@qq.com) on 10/24/14.
 */
public class MysqlDataSourceProvider implements DataSourceProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(MysqlDataSourceProvider.class);
    // 同一配置, 始终保持同一个连接
    private static final ConcurrentHashMap<String, DataSource> DATA_SOURCE_MAP = new ConcurrentHashMap<String, DataSource>();

    private static final Object lock = new Object();

    public DataSource getDataSource(Config config) {

        String url = config.getParameter(ExtConfig.JDBC_URL);
        String username = config.getParameter(ExtConfig.JDBC_USERNAME);
        String password = config.getParameter(ExtConfig.JDBC_PASSWORD);

        if (StringUtils.isEmpty(url)) {
            throw new IllegalArgumentException(ExtConfig.JDBC_URL + "JDBC_URL should not be empty");
        }
        if (StringUtils.isEmpty(ExtConfig.JDBC_USERNAME)) {
            throw new IllegalArgumentException(ExtConfig.JDBC_USERNAME + " should not be empty");
        }

        String cachedKey = url + username + password;

        DataSource dataSource = DATA_SOURCE_MAP.get(cachedKey);
        if (dataSource == null) {
            try {
                synchronized (lock) {
                    dataSource = DATA_SOURCE_MAP.get(cachedKey);
                    if (dataSource != null) {
                        return dataSource;
                    }
                    dataSource = createDruidDataSource(config);

                    DATA_SOURCE_MAP.put(cachedKey, dataSource);
                }
            } catch (Exception e) {
                throw new IllegalStateException(
                        String.format("connect datasource failed! url: %s", url), e);
            }
        }
        return dataSource;
    }

    private DataSource createDruidDataSource(Config config) {
        DruidDataSource dataSource = new DruidDataSource();
        Class<DruidDataSource> clazz = DruidDataSource.class;
        for (Map.Entry<String, Class<?>> entry : FIELDS.entrySet()) {
            String field = entry.getKey();
            String value = config.getParameter("druid." + field);
            if (StringUtils.isNotEmpty(value)) {
                Method setMethod = null;
                try {
                    try {
                        setMethod = clazz.getMethod("set" + (field.substring(0, 1).toUpperCase() + field.substring(1))
                                , entry.getValue());
                    } catch (NoSuchMethodException e) {
                        setMethod = clazz.getMethod("set" + (field.substring(0, 1).toUpperCase() + field.substring(1))
                                , PrimitiveTypeUtil.getUnBoxType(entry.getValue()));
                    }

                    Object obj = PrimitiveTypeUtil.convert(value, entry.getValue());
                    setMethod.invoke(dataSource, obj);
                } catch (Exception e) {
                    LOGGER.warn("set field[{}] failed! value is {}", field, value);
                }
            }
        }

        String url = config.getParameter(ExtConfig.JDBC_URL);
        String username = config.getParameter(ExtConfig.JDBC_USERNAME);
        String password = config.getParameter(ExtConfig.JDBC_PASSWORD);

        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    private static final Map<String, Class<?>> FIELDS = new ConcurrentHashMap<String, Class<?>>();

    static {
        FIELDS.put("initialSize", Integer.class);
        FIELDS.put("maxActive", Integer.class);
        FIELDS.put("maxIdle", Integer.class);
        FIELDS.put("minIdle", Integer.class);
        FIELDS.put("maxWait", Integer.class);
        FIELDS.put("poolPreparedStatements", Boolean.class);
        FIELDS.put("maxOpenPreparedStatements", Integer.class);
        FIELDS.put("validationQuery", String.class);
        FIELDS.put("testOnBorrow", Boolean.class);
        FIELDS.put("testOnReturn", Boolean.class);
        FIELDS.put("testWhileIdle", Boolean.class);
        FIELDS.put("timeBetweenEvictionRunsMillis", Long.class);
        FIELDS.put("numTestsPerEvictionRun", Integer.class);
        FIELDS.put("minEvictableIdleTimeMillis", Long.class);
        FIELDS.put("exceptionSorter", String.class);
        FIELDS.put("filters", String.class);
    }
}
