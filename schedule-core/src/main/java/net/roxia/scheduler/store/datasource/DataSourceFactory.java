package net.roxia.scheduler.store.datasource;

import net.roxia.scheduler.SchedulerConfig;
import net.roxia.scheduler.spi.ServiceLoader;

import javax.sql.DataSource;

/**
 * 保证一个DataSource对应一个SqlTemplate
 *
 * @author Robert HG (254963746@qq.com) on 3/8/16.
 */
public class DataSourceFactory {

    private static DataSource dataSource;

    private static final Object lock = new Object();

    public static DataSource get(SchedulerConfig config) {
        if (dataSource != null) {
            return dataSource;
        }
        synchronized (lock) {
            if (DataSourceFactory.dataSource != null) {
                return DataSourceFactory.dataSource;
            }
            DataSourceProvider dataSourceProvider =
                    ServiceLoader.load(DataSourceProvider.class, config);
            DataSource dataSource = dataSourceProvider.getDataSource(config);
            DataSourceFactory.dataSource = dataSource;
            return dataSource;
        }
    }

}
