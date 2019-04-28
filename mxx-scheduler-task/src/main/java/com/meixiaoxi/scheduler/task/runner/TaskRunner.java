package com.meixiaoxi.scheduler.task.runner;

import com.meixiaoxi.scheduler.AppContext;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: Runner
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-04-25 15:00:31
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-04-25    meixiaoxi       v1.0.0           创建
 */
public abstract class TaskRunner<Context extends AppContext> {

    Context context;
    private TaskRunner nextRunner;

    TaskRunner() {
    }

    public abstract void run();

    void runNext() {
        if (nextRunner != null) {
            nextRunner.run();
        }
    }

    public void setNext(TaskRunner next) {
        nextRunner = next;
    }

    public Context getContext() {
        return context;
    }
}
