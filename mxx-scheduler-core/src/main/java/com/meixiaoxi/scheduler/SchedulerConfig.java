package com.meixiaoxi.scheduler;

import com.meixiaoxi.scheduler.common.exception.ConfigException;
import com.meixiaoxi.scheduler.constant.ConstantsUtil;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: SchedulerConfig
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-04-29 17:43:02
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-04-29    meixiaoxi       v1.0.0           创建
 */
public class SchedulerConfig extends ConcurrentHashMap<String, Object> {
    private int port = 9088;

    public Integer getIntProperty(String key) {
        Object value = get(key);
        if (value instanceof Integer) {
            return (Integer) value;
        } else {
            throw new ConfigException("the key with property type is not Integer!");
        }
    }

    public Short getShortProperty(String key) {
        Object value = get(key);
        if (value instanceof Short) {
            return (Short) value;
        } else {
            throw new ConfigException("the key with property type is not Short!");
        }
    }

    public Long getLongProperty(String key) {
        Object value = get(key);
        if (value instanceof Long) {
            return (Long) value;
        } else {
            throw new ConfigException("the key with property type is not Long!");
        }
    }

    public Float getFloatProperty(String key) {
        Object value = get(key);
        if (value instanceof Float) {
            return (Float) value;
        } else {
            throw new ConfigException("the key with property type is not Float!");
        }
    }

    public Double getDoubleProperty(String key) {
        Object value = get(key);
        if (value instanceof Double) {
            return (Double) value;
        } else {
            throw new ConfigException("the key with property type is not Double!");
        }
    }

    public String getStringProperty(String key) {
        Object value = get(key);
        if (value != null) {
            return value + "";
        } else {
            return null;
        }
    }

    public String[] getStringArrayProperty(String key) {
        Object value = get(key);
        if (value != null) {
            return ConstantsUtil.COMMA_SPLIT_PATTERN.split(value + "");
        } else {
            return null;
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
