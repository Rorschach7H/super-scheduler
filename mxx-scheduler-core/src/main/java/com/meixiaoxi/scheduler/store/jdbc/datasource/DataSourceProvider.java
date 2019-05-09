package com.meixiaoxi.scheduler.store.jdbc.datasource;

import com.meixiaoxi.scheduler.SchedulerConfig;
import com.meixiaoxi.scheduler.common.constant.ConfigSpiKeys;
import com.meixiaoxi.scheduler.spi.SPI;

import javax.sql.DataSource;

/**
 * @author Robert HG (254963746@qq.com) on 10/24/14.
 */
@SPI(dynamicName = ConfigSpiKeys.DATABASE_SPI, defaultName = "mysql")
public interface DataSourceProvider {

    DataSource getDataSource(SchedulerConfig config);
}
