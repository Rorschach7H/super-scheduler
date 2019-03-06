package com.meixiaoxi.scheduler.config;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: Config
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-03-01 15:57:07
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-03-01    meixiaoxi       v1.0.0           创建
 */
public class CommonConfig {

    /**
     * 主节点名
     */
    private String masterName;
    /**
     * 单例redis连接地址 ip:port
     */
    private String redisUrl;
    /**
     * 单例redis连接密码
     */
    private String pwd;

    /**
     * 集群redis连接url [ip1:port1,ip2:port2,ip3:port3]
     */
    private String[] redisUrls;
    /**
     * 等待节点回复命令的时间。该时间从命令发送成功时开始计时。默认:3000
     */
    private int timeout;
    /**
     * 同任何节点建立连接时的等待超时。时间单位是毫秒。默认：10000
     */
    private int connectTimeout;
    /**
     * 当与某个节点的连接断开时，等待与其重新建立连接的时间间隔。时间单位是毫秒。默认:3000
     */
    private int reconnectTimeout;
    /**
     * 如果尝试达到 retryAttempts（命令失败重试次数） 仍然不能将命令发送至某个指定的节点时，将抛出错误。
     * 如果尝试在此限制之内发送成功，则开始启用 timeout（命令等待超时） 计时。默认值：3
     */
    private int retryAttempts;
    /**
     * 在一条命令发送失败以后，等待重试发送的时间间隔。时间单位是毫秒。默认值：1500
     */
    private int retryInterval;

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getRedisUrl() {
        return redisUrl;
    }

    public void setRedisUrl(String redisUrl) {
        this.redisUrl = redisUrl;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String[] getRedisUrls() {
        return redisUrls;
    }

    public void setRedisUrls(String[] redisUrls) {
        this.redisUrls = redisUrls;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getReconnectTimeout() {
        return reconnectTimeout;
    }

    public void setReconnectTimeout(int reconnectTimeout) {
        this.reconnectTimeout = reconnectTimeout;
    }

    public int getRetryAttempts() {
        return retryAttempts;
    }

    public void setRetryAttempts(int retryAttempts) {
        this.retryAttempts = retryAttempts;
    }

    public int getRetryInterval() {
        return retryInterval;
    }

    public void setRetryInterval(int retryInterval) {
        this.retryInterval = retryInterval;
    }
}
