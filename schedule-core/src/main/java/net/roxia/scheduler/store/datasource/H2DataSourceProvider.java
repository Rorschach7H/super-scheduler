package net.roxia.scheduler.store.datasource;

import net.roxia.scheduler.SchedulerConfig;
import net.roxia.scheduler.constant.ConfigKeys;
import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;

/**
 * @author Robert HG (254963746@qq.com) on 10/24/14.
 */
public class H2DataSourceProvider extends AbsreactDataSourceProvider {

    DataSource createDataSource(SchedulerConfig config) throws ClassNotFoundException {
        JdbcDataSource dataSource = new JdbcDataSource();
        Class.forName(config.getProperty(ConfigKeys.JDBC_DRIVER_CLASS_NAME));
        dataSource.setUrl(config.getProperty(ConfigKeys.JDBC_URL));
        dataSource.setUser(config.getProperty(ConfigKeys.JDBC_USERNAME));
        dataSource.setPassword(config.getProperty(ConfigKeys.JDBC_PASSWORD));

        return dataSource;
    }

}
