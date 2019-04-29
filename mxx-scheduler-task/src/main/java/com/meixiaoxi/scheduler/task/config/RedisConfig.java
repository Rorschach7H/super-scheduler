package com.meixiaoxi.scheduler.task.config;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: RedisConfig
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-04-25 10:35:54
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-04-25    meixiaoxi       v1.0.0           创建
 */
public class RedisConfig {
    /**
     * redis连接相关配置
     */
    private String address = "localhost:6379";
    private Integer database;
    private String password = "";
    private Integer timeout;

    private String sentinelMaster;
    private String[] sentinelNodes;

    private String[] clusterNodes;
    private Integer clusterMaxRedirects;

    private Integer maxIdle;
    private Integer minIdle;
    private Integer maxActive;
    private Integer maxWait;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getDatabase() {
        return database;
    }

    public void setDatabase(Integer database) {
        this.database = database;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public String getSentinelMaster() {
        return sentinelMaster;
    }

    public void setSentinelMaster(String sentinelMaster) {
        this.sentinelMaster = sentinelMaster;
    }

    public String[] getSentinelNodes() {
        return sentinelNodes;
    }

    public void setSentinelNodes(String[] sentinelNodes) {
        this.sentinelNodes = sentinelNodes;
    }

    public String[] getClusterNodes() {
        return clusterNodes;
    }

    public void setClusterNodes(String[] clusterNodes) {
        this.clusterNodes = clusterNodes;
    }

    public Integer getClusterMaxRedirects() {
        return clusterMaxRedirects;
    }

    public void setClusterMaxRedirects(Integer clusterMaxRedirects) {
        this.clusterMaxRedirects = clusterMaxRedirects;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    public Integer getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }

    public Integer getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(Integer maxWait) {
        this.maxWait = maxWait;
    }
}
