package com.meixiaoxi.scheduler.core.exception;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: TaskTimeException
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-03-01 15:07:56
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-03-01    meixiaoxi       v1.0.0           创建
 */
public class TaskTimeException extends RuntimeException {

    public TaskTimeException() {
        super();
    }

    public TaskTimeException(String message) {
        super(message);
    }

    public TaskTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskTimeException(Throwable cause) {
        super(cause);
    }

    protected TaskTimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
