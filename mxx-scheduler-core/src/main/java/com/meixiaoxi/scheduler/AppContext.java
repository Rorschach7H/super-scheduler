package com.meixiaoxi.scheduler;

import com.meixiaoxi.scheduler.core.processor.TaskProcessor;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: SchedulerContext
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-04-25 14:43:22
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-04-25    meixiaoxi       v1.0.0           创建
 */
public abstract class AppContext {

    /**
     * 系统配置
     */
    private SchedulerConfig config;

    /**
     * 任务处理
     */
    private TaskProcessor taskProcessor;

    public AppContext(SchedulerConfig config) {
        this.config = config;
    }

    public AppContext(SchedulerConfig config, TaskProcessor taskProcessor) {
        this.config = config;
        this.taskProcessor = taskProcessor;
    }

    public SchedulerConfig getConfig() {
        return config;
    }

    public void setConfig(SchedulerConfig config) {
        this.config = config;
    }

    public TaskProcessor getTaskProcessor() {
        return taskProcessor;
    }

    public void setTaskProcessor(TaskProcessor taskProcessor) {
        this.taskProcessor = taskProcessor;
    }
}
