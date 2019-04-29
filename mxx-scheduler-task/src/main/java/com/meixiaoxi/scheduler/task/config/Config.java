package com.meixiaoxi.scheduler.task.config;

import com.alibaba.fastjson.JSON;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: Config
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-04-26 10:49:33
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-04-26    meixiaoxi       v1.0.0           创建
 */
public class Config {

    /**
     * 消费者连接端口
     */
    private int port;

    private DatabaseConfig databaseConfig;
    private RedisConfig redisConfig;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public DatabaseConfig getDatabaseConfig() {
        return databaseConfig;
    }

    public void setDatabaseConfig(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    public RedisConfig getRedisConfig() {
        return redisConfig;
    }

    public void setRedisConfig(RedisConfig redisConfig) {
        this.redisConfig = redisConfig;
    }
}
