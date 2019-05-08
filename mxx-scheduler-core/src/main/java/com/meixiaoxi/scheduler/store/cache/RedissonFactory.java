package com.meixiaoxi.scheduler.store.cache;

import com.meixiaoxi.scheduler.SchedulerConfig;
import com.meixiaoxi.scheduler.store.cache.exception.RedissonException;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: RedissonManager
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-03-01 16:08:26
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-03-01    meixiaoxi       v1.0.0           创建
 */
public final class RedissonFactory {

    private static final Object lock = new Object();

    private static RedissonClient redissonClient;

    /**
     * 初始化Redisson
     */
    private static void init(SchedulerConfig config) {
        try {
            Config redissonConfig = new Config();
//            redissonConfig.useSentinelServers()
//                    .setMasterName(config.getParameter(ExtConfig))
//                    .addSentinelAddress(redisConfig.getRedisUrls())
//                    //同任何节点建立连接时的等待超时。时间单位是毫秒。默认：10000
//                    .setConnectTimeout(redisConfig.getConnectTimeout())
//                    //如果尝试达到 retryAttempts（命令失败重试次数） 仍然不能将命令发送至某个指定的节点时，将抛出错误。如果尝试在此限制之内发送成功，则开始启用 timeout（命令等待超时） 计时。默认值：3
//                    .setRetryAttempts(5)
//                    //在一条命令发送失败以后，等待重试发送的时间间隔。时间单位是毫秒。     默认值：1500
//                    .setRetryInterval(redisConfig.getRetryInterval());
            redissonClient = Redisson.create(redissonConfig);
        } catch (Exception e) {
            throw new RuntimeException("连接redis出现异常", e);
        }
    }

    /**
     * 获取Redisson的实例对象（单例）
     *
     * @return
     */
    public static RedissonClient getRedissonClient(SchedulerConfig redisConfig) {
        if (redissonClient == null) {
            synchronized (lock) {
                if (redissonClient == null) {
                    init(redisConfig);
                }
            }
        }
        return redissonClient;
    }

    public static RedissonClient getRedissonClient() {
        if (redissonClient == null) {
            throw new RedissonException("redis接连器未初始化！请检查是否已经连接redis成功！");
        }
        return redissonClient;
    }
}
