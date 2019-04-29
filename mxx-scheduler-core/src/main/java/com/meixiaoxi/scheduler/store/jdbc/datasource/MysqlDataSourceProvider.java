package com.meixiaoxi.scheduler.store.jdbc.datasource;

import com.meixiaoxi.scheduler.SchedulerConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.util.concurrent.ConcurrentHashMap;

/**
 * druid相关的配置使用 druid. 开头即可
 *
 * @author Robert HG (254963746@qq.com) on 10/24/14.
 */
public class MysqlDataSourceProvider implements DataSourceProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(MysqlDataSourceProvider.class);
    // 同一配置, 始终保持同一个连接
    private static final ConcurrentHashMap<String, DataSource> DATA_SOURCE_MAP = new ConcurrentHashMap<>();

    private static final Object lock = new Object();

    public DataSource getDataSource(SchedulerConfig config) {

        String url = "";
        String username = "";
        String password = "";

        if (StringUtils.isEmpty(url)) {
            throw new IllegalArgumentException("jdbc url should not be empty");
        }
        if (StringUtils.isEmpty(username)) {
            throw new IllegalArgumentException("jdbc url should not be empty");
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

                    DATA_SOURCE_MAP.put(cachedKey, dataSource);
                }
            } catch (Exception e) {
                throw new IllegalStateException(
                        String.format("connect datasource failed! url: %s", url), e);
            }
        }
        return dataSource;
    }
}
