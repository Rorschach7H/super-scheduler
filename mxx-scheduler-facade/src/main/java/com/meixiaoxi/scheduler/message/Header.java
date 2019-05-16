package com.meixiaoxi.scheduler.message;

import java.io.Serializable;

// 消息的头部
public class Header implements Serializable {

    //协议版本
    private int version;
    //消息内容长度
    private int contentLength;
    //连接KEY
    private String key;
    //客户端名
    private String name;
    //客户端别名
    private String alias;

    public Header(int version, int contentLength, String key) {
        this.version = version;
        this.contentLength = contentLength;
        this.key = key;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
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
}