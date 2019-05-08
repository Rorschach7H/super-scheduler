package com.meixiaoxi.scheduler.task.runner;

import com.meixiaoxi.scheduler.SchedulerConfig;
import com.meixiaoxi.scheduler.task.TaskAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private SchedulerConfig config;

    public InitContextRunner(SchedulerConfig config) {
        this.config = config;
    }

    @Override
    protected void run(TaskAppContext context) {
        log.info("initContextRunner start...");
        config.put("hello", "world");
    }

    public void start() {
        this.context = new TaskAppContext(config);
        start(context);
    }
}
