package com.meixiaoxi.scheduler.store.datasource;

import com.meixiaoxi.scheduler.SchedulerConfig;
import com.meixiaoxi.scheduler.constant.ConfigKeys;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.lang3.StringUtils;

import javax.sql.DataSource;

/**
 * druid相关的配置使用 druid. 开头即可
 *
 * @author Robert HG (254963746@qq.com) on 10/24/14.
 */
public class MysqlDataSourceProvider extends AbsreactDataSourceProvider {

    DataSource createDataSource(SchedulerConfig config) {
        HikariConfig hikariConfig = new HikariConfig();
        String driverClassName = config.getProperty(ConfigKeys.jdbc_driver_class_name);
        hikariConfig.setDriverClassName(StringUtils.isBlank(driverClassName) ?
                "com.mysql.cj.jdbc.Driver" : driverClassName);
        hikariConfig.setJdbcUrl(config.getProperty(ConfigKeys.jdbc_url));
        hikariConfig.setUsername(config.getProperty(ConfigKeys.jdbc_username));
        hikariConfig.setPassword(config.getProperty(ConfigKeys.jdbc_password));
        Long connectionTimeout = config.getProperty(ConfigKeys.jdbc_connection_timeout, Long.class);
        if (connectionTimeout != null) {
            hikariConfig.setConnectionTimeout(connectionTimeout);
        }
        Boolean readOnly = config.getProperty(ConfigKeys.jdbc_read_only, Boolean.class);
        if (readOnly != null) {
            hikariConfig.setReadOnly(readOnly);
        }
        Long idleTimeout = config.getProperty(ConfigKeys.jdbc_idle_timeout, Long.class);
        if (idleTimeout != null) {
            hikariConfig.setIdleTimeout(idleTimeout);
        }
        Long maxLifetime = config.getProperty(ConfigKeys.jdbc_max_life_time, Long.class);
        if (maxLifetime != null) {
            hikariConfig.setMaxLifetime(maxLifetime);
        }
        Integer maximumPoolSize = config.getProperty(ConfigKeys.jdbc_maximum_pool_size, Integer.class);
        if (maximumPoolSize != null) {
            hikariConfig.setMaximumPoolSize(maximumPoolSize);
        }

        return new HikariDataSource(hikariConfig);
    }
}
