package net.roxia.scheduler.redis;

import net.roxia.scheduler.redis.exception.RedissonException;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.io.File;
import java.io.IOException;
import java.net.URL;

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

    private final static File redissonConfig;

    private static RedissonClient redissonClient;

    static {
        ClassLoader classLoader = RedissonFactory.class.getClassLoader();
        //getResource()方法会去classpath下找这个文件，获取到url resource, 得到这个资源后，调用url.getFile获取到 文件 的绝对路径
        URL url = classLoader.getResource("redisson.yml");
        if (url != null && url.getFile() != null) {
            redissonConfig = new File(url.getFile());
        } else {
            throw new RedissonException("load redisson config error, content is empty!");
        }

    }

    public static RedissonClient getRedissonClient() {
        if (redissonClient == null) {
            synchronized (RedissonFactory.class) {
                if (redissonClient == null) {
                    init();
                }
            }
        }
        return redissonClient;
    }

    /**
     * 初始化Redisson
     */
    private static void init() {
        try {
            Config config = Config.fromYAML(RedissonFactory.redissonConfig);
            redissonClient = Redisson.create(config);
        } catch (IOException e) {
            throw new RedissonException("parse redisson config error! may be error yml form! ", e);
        }
    }
}
