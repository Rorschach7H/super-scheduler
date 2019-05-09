package com.meixiaoxi.scheduler.config;

import com.meixiaoxi.scheduler.SchedulerConfig;
import junit.framework.TestCase;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: SchedulerConfigTest
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-08 15:19:02
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-08    meixiaoxi       v1.0.0           创建
 */
public class SchedulerConfigTest extends TestCase {

    public void testGetProperty(){
        SchedulerConfig config = new SchedulerConfig();
        config.put("hello1", "world");
        config.put("hello2", 2);
        config.put("hello3", 3L);
        config.put("hello4", 4F);
        config.put("hello5", new SchedulerConfig());
        System.out.println(config.getProperty("hello1", String.class));
        System.out.println(config.getProperty("hello2", Integer.class));
        System.out.println(config.getProperty("hello3", Long.class));
        System.out.println(config.getProperty("hello4", Float.class));
        System.out.println(config.getProperty("hello4", SchedulerConfig.class));
    }
}
