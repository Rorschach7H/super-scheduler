package com.meixiaoxi.scheduler.spi;

import junit.framework.TestCase;

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
        TestService testService = ServiceLoader.load(TestService.class, "service1");
        testService.sayHello();
    }
}
