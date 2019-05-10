package com.meixiaoxi.scheduler.store.jdbc.datasource;

import com.meixiaoxi.scheduler.SchedulerConfig;
import com.meixiaoxi.scheduler.constant.ConfigKeys;
import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;

/**
 * @author Robert HG (254963746@qq.com) on 10/24/14.
 */
public class H2DataSourceProvider extends AbsreactDataSourceProvider {

    DataSource createDataSource(SchedulerConfig config) throws ClassNotFoundException {
        JdbcDataSource dataSource = new JdbcDataSource();
        Class.forName(config.getProperty(ConfigKeys.jdbc_driver_class_name));
        dataSource.setUrl(config.getProperty(ConfigKeys.jdbc_url));
        dataSource.setUser(config.getProperty(ConfigKeys.jdbc_username));
        dataSource.setPassword(config.getProperty(ConfigKeys.jdbc_password));

        return dataSource;
    }

}
