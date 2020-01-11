package net.roxia.scheduler.task.server;

import io.netty.channel.Channel;

import java.util.Date;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: ConnectingClient
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-16 17:52:04
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-16    meixiaoxi       v1.0.0           创建
 */
public class ConnectingClient {
    private String ip;
    private String key;
    private String name;
    private String alias;
    private Date connectTime;
    private Channel channel;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Date getConnectTime() {
        return connectTime;
    }

    public void setConnectTime(Date connectTime) {
        this.connectTime = connectTime;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
