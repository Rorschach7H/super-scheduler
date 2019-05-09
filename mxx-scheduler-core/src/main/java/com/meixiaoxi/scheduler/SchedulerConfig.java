package com.meixiaoxi.scheduler;

import com.meixiaoxi.scheduler.common.exception.ConfigException;

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

    @SuppressWarnings("unchecked")
    public <T> T getProperty(String key, Class<T> clazz) {
        Object value = get(key);
        if (value != null) {
            if (clazz == value.getClass()) {
                return (T) value;
            } else {
                throw new ConfigException("The value type with key-" + key + " is not [" + clazz.getName() + "]!");
            }
        }
        return null;
    }

    public String getProperty(String key) {
        Object value = get(key);
        return value == null ? null : value + "";
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
