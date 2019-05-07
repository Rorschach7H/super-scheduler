package com.meixiaoxi.scheduler.task.runner;


import com.meixiaoxi.scheduler.task.TaskAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: TaskRunner
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-04-25 14:29:43
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-04-25    meixiaoxi       v1.0.0           创建
 */
public class ScanQueueTaskRunner extends TaskRunner<TaskAppContext> {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    public ScanQueueTaskRunner(TaskAppContext context) {
        this.context = context;
    }

    @Override
    protected void run() {
        log.info("scanQueueTaskRunner start...");
    }
}