package net.roxia.scheduler;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: TaskException
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-03-01 15:07:56
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-03-01    meixiaoxi       v1.0.0           创建
 */
public class TaskException extends RuntimeException {

    public TaskException() {
        super();
    }

    public TaskException(String message) {
        super(message);
    }

    public TaskException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskException(Throwable cause) {
        super(cause);
    }

    protected TaskException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}