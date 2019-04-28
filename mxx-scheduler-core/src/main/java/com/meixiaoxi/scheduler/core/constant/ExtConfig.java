package com.meixiaoxi.scheduler.core.constant;

/**
 * @author Robert HG (254963746@qq.com) on 4/23/16.
 */
public interface ExtConfig {
    /**
     * JDBC链接相关配置
     */
    String JDBC_URL = "jdbc.url";
    String JDBC_USERNAME = "jdbc.username";
    String JDBC_PASSWORD = "jdbc.password";

    String NEED_CREATE_DB_TABLE = "jdbc.create.db.table";
    /**
     * Durid相关数据的配置
     */
    String DRUID_initialSize = "druid.initialSize";
    String DRUID_maxActive = "druid.maxActive";
    String DRUID_maxIdle = "druid.maxIdle";
    String DRUID_minIdle = "druid.minIdle";
    String DRUID_maxWait = "druid.maxWait";
    String DRUID_poolPreparedStatements = "druid.poolPreparedStatements";
    String DRUID_maxOpenPreparedStatements = "druid.maxOpenPreparedStatements";
    String DRUID_validationQuery = "druid.validationQuery";
    String DRUID_testOnBorrow = "druid.testOnBorrow";
    String DRUID_testOnReturn = "druid.testOnReturn";
    String DRUID_testWhileIdle = "druid.testWhileIdle";
    String DRUID_timeBetweenEvictionRunsMillis = "druid.timeBetweenEvictionRunsMillis";
    String DRUID_numTestsPerEvictionRun = "druid.numTestsPerEvictionRun";
    String DRUID_minEvictableIdleTimeMillis = "druid.minEvictableIdleTimeMillis";
    String DRUID_exceptionSorter = "druid.exceptionSorter";
    String DRUID_filters = "druid.filters";
}
