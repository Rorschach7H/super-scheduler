package net.roxia.scheduler.core.handler;

import net.roxia.scheduler.core.task.domain.RunExecutingTask;

/**
 * 任务执行逻辑接口
 * 具体实现需要使用方实现该接口
 */
public interface TaskExecuteHandler {
    boolean executeTask(RunExecutingTask task);
}