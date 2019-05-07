package com.meixiaoxi.scheduler.task.runner;

import com.meixiaoxi.scheduler.SchedulerConfig;
import com.meixiaoxi.scheduler.task.TaskAppContext;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: InitContextRunner
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-04-28 14:33:00
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-04-28    meixiaoxi       v1.0.0           创建
 */
public class InitContextRunner extends TaskRunner<TaskAppContext> {

    private SchedulerConfig config;

    public InitContextRunner(SchedulerConfig config) {
        this.config = config;
    }

    @Override
    public void run() {
        context = new TaskAppContext(config);
        runNext();
    }
}
