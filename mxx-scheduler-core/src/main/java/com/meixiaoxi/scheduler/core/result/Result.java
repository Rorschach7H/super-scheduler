package com.meixiaoxi.scheduler.core.result;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: Result
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-04-25 15:02:40
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-04-25    meixiaoxi       v1.0.0           创建
 */
public class Result {
    private Action action;
    private String msg;

    public Result(Action action, String msg) {
        this.action = action;
        this.msg = msg;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
