package com.meixiaoxi.scheduler.core.handler;

import com.meixiaoxi.scheduler.core.task.domain.TaskPo;

/**
 * 任务执行逻辑接口
 * 具体实现需要使用方实现该接口
 */
public interface TaskExecuteHandler {
    boolean executeTask(TaskPo task);
}