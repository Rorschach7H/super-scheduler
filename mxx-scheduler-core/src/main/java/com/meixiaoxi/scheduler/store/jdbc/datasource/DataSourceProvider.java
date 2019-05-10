package com.meixiaoxi.scheduler.store.jdbc.datasource;

import com.meixiaoxi.scheduler.SchedulerConfig;
import com.meixiaoxi.scheduler.constant.ConfigSpiKeys;
import com.meixiaoxi.scheduler.spi.SPI;

import javax.sql.DataSource;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: DataSourceProvider
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-10 11:43:20
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-10    meixiaoxi       v1.0.0           创建
 */
@SPI(dynamicKey = ConfigSpiKeys.DATABASE_SPI, defaultName = "mysql")
public interface DataSourceProvider {
    DataSource getDataSource(SchedulerConfig config);
}
