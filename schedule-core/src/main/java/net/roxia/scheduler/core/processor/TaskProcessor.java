package net.roxia.scheduler.core.processor;

import net.roxia.scheduler.core.handler.TaskExecuteHandler;
import net.roxia.scheduler.core.task.domain.RunExecutingTask;

import java.util.List;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: TaskProcessor
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-15 18:16:20
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-15    meixiaoxi       v1.0.0           创建
 */
public interface TaskProcessor {

    boolean addTask(RunExecutingTask taskInfo);

    List<RunExecutingTask> executeTask(String taskGroup, TaskExecuteHandler handler);

    void addUnReadyTaskToQueue(List<RunExecutingTask> tasks);
}
