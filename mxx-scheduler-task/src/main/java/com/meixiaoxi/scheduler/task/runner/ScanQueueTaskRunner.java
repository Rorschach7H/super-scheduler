package com.meixiaoxi.scheduler.task.runner;


import com.meixiaoxi.scheduler.core.handler.TaskExecuteHandler;
import com.meixiaoxi.scheduler.core.result.Result;
import com.meixiaoxi.scheduler.task.TaskAppContext;
import com.meixiaoxi.scheduler.task.annotation.EnabledTaskHandler;

import java.util.ServiceLoader;

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
public class ScanQueueTaskRunner implements TaskRunner {
    @Override
    public Result run(TaskAppContext context) {

        TaskRunner taskRunner = new NettyServerRunner();
        taskRunner.run(context);

        return null;
    }

    private void registerTaskHandler(TaskAppContext context) {
        ServiceLoader<TaskExecuteHandler> loader = ServiceLoader.load(TaskExecuteHandler.class);
        loader.forEach(taskExecuteHandler -> {
            boolean hasAnnotation = taskExecuteHandler.getClass().isAnnotationPresent(EnabledTaskHandler.class);
            if (hasAnnotation) {
                EnabledTaskHandler clazzAnnotation = taskExecuteHandler.getClass().getAnnotation(EnabledTaskHandler.class);
                String taskGroup = clazzAnnotation.taskGroup();
                context.putTaskExecuteHandler(taskGroup, taskExecuteHandler.getClass().getName());
            }
        });
    }
}