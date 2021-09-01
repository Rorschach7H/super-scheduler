package net.roxia.scheduler.store.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import net.roxia.scheduler.SchedulerConfig;
import net.roxia.scheduler.constant.ConfigKeys;
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
        String driverClassName = config.getProperty(ConfigKeys.JDBC_DRIVER_CLASS_NAME);
        hikariConfig.setDriverClassName(StringUtils.isBlank(driverClassName) ?
                "com.mysql.cj.jdbc.Driver" : driverClassName);
        hikariConfig.setJdbcUrl(config.getProperty(ConfigKeys.JDBC_URL));
        hikariConfig.setUsername(config.getProperty(ConfigKeys.JDBC_USERNAME));
        hikariConfig.setPassword(config.getProperty(ConfigKeys.JDBC_PASSWORD));
        Long connectionTimeout = config.getProperty(ConfigKeys.JDBC_CONNECTION_TIMEOUT, Long.class);
        if (connectionTimeout != null) {
            hikariConfig.setConnectionTimeout(connectionTimeout);
        }
        Boolean readOnly = config.getProperty(ConfigKeys.JDBC_READ_ONLY, Boolean.class);
        if (readOnly != null) {
            hikariConfig.setReadOnly(readOnly);
        }
        Long idleTimeout = config.getProperty(ConfigKeys.JDBC_IDLE_TIMEOUT, Long.class);
        if (idleTimeout != null) {
            hikariConfig.setIdleTimeout(idleTimeout);
        }
        Long maxLifetime = config.getProperty(ConfigKeys.JDBC_MAX_LIFE_TIME, Long.class);
        if (maxLifetime != null) {
            hikariConfig.setMaxLifetime(maxLifetime);
        }
        Integer maximumPoolSize = config.getProperty(ConfigKeys.JDBC_MAXIMUM_POOL_SIZE, Integer.class);
        if (maximumPoolSize != null) {
            hikariConfig.setMaximumPoolSize(maximumPoolSize);
        }

        return new HikariDataSource(hikariConfig);
    }
}
