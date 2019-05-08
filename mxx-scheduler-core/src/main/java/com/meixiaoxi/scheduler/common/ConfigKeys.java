package com.meixiaoxi.scheduler.common;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: ConfigKeys
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-08 15:01:39
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-08    meixiaoxi       v1.0.0           创建
 */
public enum ConfigKeys {

    JDBC_URL("jdbc.url", String.class),
    JDBC_USERNAME("jdbc.username", String.class),
    JDBC_PASSWORD("jdbc.password", String.class),

    DRUID_INITIAL_SIZE("druid.initialSize", Integer.class),
    DRUID_MAX_ACTIVE("druid.maxActive", Integer.class),
    DRUID_MAX_IDLE("druid.maxIdle", Integer.class),
    DRUID_MIN_IDLE("druid.minIdle", Integer.class),
    DRUID_MAX_WAIT("druid.maxWait", Integer.class),
    DRUID_POOL_PREPARED_STATEMENTS("druid.poolPreparedStatements", Integer.class),
    DRUID_MAX_OPEN_PREPARED_STATEMENTS("druid.maxOpenPreparedStatements", Integer.class),
    DRUID_VALIDATION_QUERY("druid.validationQuery", Integer.class),
    DRUID_TEST_ON_BORROW("druid.testOnBorrow", Integer.class),
    DRUID_TEST_ON_RETURN("druid.testOnReturn", Integer.class),
    DRUID_TEST_WHILE_IDLE("druid.testWhileIdle", Integer.class),
    DRUID_TIME_BETWEEN_EVICTION_RUNS_MILLIS("druid.timeBetweenEvictionRunsMillis", Integer.class),
    DRUID_NUM_TESTS_PER_EVICTION_RUN("druid.numTestsPerEvictionRun", Integer.class),
    DRUID_MIN_EVICTABLE_IDLE_TIME_MILLIS("druid.minEvictableIdleTimeMillis", Integer.class),
    DRUID_EXCEPTION_SORTER("druid.exceptionSorter", Integer.class),
    DRUID_FILTERS("druid.filters", Integer.class),

    REDIS_ADDRESS("redis.address", String.class),
    REDIS_DATABASE("redis.database", Integer.class),
    REDIS_PASSWORD("redis.password", String.class),
    REDIS_TIMEOUT("redis.timeout", Long.class),
    REDIS_SENTINEL_MASTER("redis.sentinelMaster", String.class),
    REDIS_SENTINEL_NODES("redis.sentinelNodes", String.class),
    REDIS_CLUSTER_NODES("redis.clusterNodes", String.class),
    REDIS_CLUSTER_MAX_REDIRECTS("redis.clusterMaxRedirects", Integer.class),
    REDIS_MAX_IDLE("redis.maxIdle", String.class),
    REDIS_MAX_ACTIVE("redis.maxActive", String.class),
    REDIS_MIN_IDLE("redis.minIdle", String.class),
    REDIS_MAX_WAIT("redis.maxWait", String.class),
    ;

    private String key;
    private Class type;

    ConfigKeys(String key, Class type) {
        this.key = key;
        this.type = type;
    }
}
