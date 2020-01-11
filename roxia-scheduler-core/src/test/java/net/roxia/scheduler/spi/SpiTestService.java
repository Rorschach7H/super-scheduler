package net.roxia.scheduler.spi;

import junit.framework.TestCase;
import net.roxia.scheduler.SchedulerConfig;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: SpiTestService
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-09 17:14:49
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-09    meixiaoxi       v1.0.0           创建
 */
public class SpiTestService extends TestCase {

    public void testLoad() {
        SchedulerConfig config = new SchedulerConfig();
        config.put("testService", "service1");
        TestService testService = ServiceLoader.load(TestService.class, config);
        testService.sayHello();
        config.put("testService", "service2");
        TestService testService2 = ServiceLoader.load(TestService.class, config);
        testService2.sayHello();
    }
}
