package com.meixiaoxi.scheduler.store.redisson;

import com.meixiaoxi.scheduler.SchedulerConfig;
import com.meixiaoxi.scheduler.constant.ConfigKeys;
import com.meixiaoxi.scheduler.store.cache.RedissonFactory;
import junit.framework.TestCase;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: RedissonTest
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-10 17:26:42
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-10    meixiaoxi       v1.0.0           创建
 */
public class RedissonTest extends TestCase {
    public void testCreateRedissonClient() {
        SchedulerConfig config = new SchedulerConfig();
        config.put(ConfigKeys.rdss_address, "redis://meixiaoxi.com:6379");
        config.put(ConfigKeys.rdss_password, "hjw556677");
        RedissonClient client = RedissonFactory.getRedissonClient(config);
        RList<String> list = client.getList("hello world");
//        list.add("world1");
//        list.add("world2");
//        list.add("world3");
//        list.add("world4");
//
        System.out.println(list);

    }
}
