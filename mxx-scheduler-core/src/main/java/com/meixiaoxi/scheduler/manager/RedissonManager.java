package com.meixiaoxi.scheduler.manager;

import com.meixiaoxi.scheduler.config.CommonConfig;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

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
public class RedissonManager implements ApplicationContextAware {

    private static final Object lock = new Object();

    private static RedissonClient redissonClient;
    private static CommonConfig commonConfig;

    /**
     * 初始化Redisson
     */
    private static void init() {
        try {
            Config config = new Config();
            config.useSentinelServers()
                    .setMasterName(commonConfig.getMasterName())
                    .addSentinelAddress(commonConfig.getRedisUrls())
                    //同任何节点建立连接时的等待超时。时间单位是毫秒。默认：10000
                    .setConnectTimeout(commonConfig.getConnectTimeout())
                    //如果尝试达到 retryAttempts（命令失败重试次数） 仍然不能将命令发送至某个指定的节点时，将抛出错误。如果尝试在此限制之内发送成功，则开始启用 timeout（命令等待超时） 计时。默认值：3
                    .setRetryAttempts(5)
                    //在一条命令发送失败以后，等待重试发送的时间间隔。时间单位是毫秒。     默认值：1500
                    .setRetryInterval(commonConfig.getRetryInterval());
            redissonClient = Redisson.create(config);
        } catch (Exception e) {
            throw new RuntimeException("连接redis出现异常", e);
        }
    }

    /**
     * 获取Redisson的实例对象（单例）
     *
     * @return
     */
    public static RedissonClient getRedissonClient() {
        if (redissonClient == null) {
            synchronized (lock) {
                if (redissonClient == null) {
                    init();
                }
            }
        }
        return redissonClient;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        commonConfig = applicationContext.getBean(CommonConfig.class);
    }
}
