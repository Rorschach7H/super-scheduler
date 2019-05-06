package com.meixiaoxi.scheduler.task;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: Task
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-06 09:36:28
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-06    meixiaoxi       v1.0.0           创建
 */
public class Task {

    private final Runnable runnable;

    public Task(Runnable runnable) {
        this.runnable = runnable;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    @Override
    public String toString() {
        return runnable.toString();
    }
}
