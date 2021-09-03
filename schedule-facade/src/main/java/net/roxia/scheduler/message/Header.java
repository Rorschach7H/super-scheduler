package net.roxia.scheduler.message;

import java.io.Serializable;

/**
 * 消息的头部
 */
public class Header implements Serializable {

    /**
     * 协议版本
     */
    private int version;
    /**
     * 连接KEY
     */
    private String key;
    /**
     * 客户端名
     */
    private String name;
    /**
     * 客户端别名
     */
    private String alias;
    /**
     * 消息类型
     *
     * @see net.roxia.scheduler.adapter.enums.OperateEnum
     */
    private String type;

    public Header(int version, String key) {
        this.version = version;
        this.key = key;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}