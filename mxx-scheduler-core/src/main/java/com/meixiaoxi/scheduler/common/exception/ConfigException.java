package com.meixiaoxi.scheduler.common.exception;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: ConfigException
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-04-30 17:20:18
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-04-30    meixiaoxi       v1.0.0           创建
 */
public class ConfigException extends RuntimeException {
    public ConfigException() {
    }

    public ConfigException(String message) {
        super(message);
    }

    public ConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfigException(Throwable cause) {
        super(cause);
    }

    public ConfigException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
