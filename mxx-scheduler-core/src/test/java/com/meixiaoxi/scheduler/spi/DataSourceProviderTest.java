package com.meixiaoxi.scheduler.spi;

import com.meixiaoxi.scheduler.SchedulerConfig;
import com.meixiaoxi.scheduler.constant.ConfigSpiKeys;
import com.meixiaoxi.scheduler.store.datasource.DataSourceProvider;
import junit.framework.TestCase;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: DataSourceProviderTest
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-10 11:40:29
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-10    meixiaoxi       v1.0.0           创建
 */
public class DataSourceProviderTest extends TestCase {

    public void testProvider() {
        SchedulerConfig config = new SchedulerConfig();
        config.put(ConfigSpiKeys.DATABASE_SPI, "mysql");
        DataSourceProvider provider = ServiceLoader.load(DataSourceProvider.class, config);
        provider.getDataSource(config);
    }
}
