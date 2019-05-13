package com.meixiaoxi.scheduler.task;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: CfgException
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-13 11:17:02
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-13    meixiaoxi       v1.0.0           创建
 */
public class CfgException extends RuntimeException {

    public CfgException() {
    }

    public CfgException(String message) {
        super(message);
    }

    public CfgException(String message, Throwable cause) {
        super(message, cause);
    }

    public CfgException(Throwable cause) {
        super(cause);
    }
}
