package com.meixiaoxi.scheduler.store.jdbc.datasource;

import com.meixiaoxi.scheduler.core.Config;
import com.meixiaoxi.scheduler.spi.SPI;

import javax.sql.DataSource;

/**
 * @author Robert HG (254963746@qq.com) on 10/24/14.
 */
@SPI(defaultValue = "mysql")
public interface DataSourceProvider {

    DataSource getDataSource(Config config);

}
