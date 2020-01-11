package net.roxia.scheduler.spi;

import junit.framework.TestCase;
import net.roxia.scheduler.SchedulerConfig;
import net.roxia.scheduler.constant.ConfigSpiKeys;
import net.roxia.scheduler.store.datasource.DataSourceProvider;

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
