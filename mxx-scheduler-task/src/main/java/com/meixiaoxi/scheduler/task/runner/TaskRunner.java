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
    private TaskRunner<Context> nextRunner;

    TaskRunner() {
    }

    protected abstract void run(Context context);

    public void setNext(TaskRunner<Context> next) {
        nextRunner = next;
    }

    public Context getContext() {
        return context;
    }

    public void start(Context context) {
        run(context);
        this.context = context;
        if (nextRunner != null) {
            nextRunner.start(context);
        }
    }
}
