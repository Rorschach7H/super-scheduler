package com.meixiaoxi.scheduler.core.config;

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
public class Config extends CommonConfig {

    private RedisConfig redisConfig;
    private MysqlConfig mysqlConfig;

    public RedisConfig getRedisConfig() {
        return redisConfig;
    }

    public void setRedisConfig(RedisConfig redisConfig) {
        this.redisConfig = redisConfig;
    }

    public MysqlConfig getMysqlConfig() {
        return mysqlConfig;
    }

    public void setMysqlConfig(MysqlConfig mysqlConfig) {
        this.mysqlConfig = mysqlConfig;
    }
}
