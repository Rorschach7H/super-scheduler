package com.meixiaoxi.scheduler.consumer.runner;


import com.meixiaoxi.scheduler.consumer.annotation.EnabledTaskHandler;
import com.meixiaoxi.scheduler.context.TaskContext;
import com.meixiaoxi.scheduler.handler.TaskExecuteHandler;
import com.meixiaoxi.scheduler.processor.TaskProcessor;
import com.meixiaoxi.scheduler.result.Result;
import com.meixiaoxi.scheduler.runner.TaskRunner;

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
public class ConsumerTaskRunner implements TaskRunner {

    @Override
    public Result run(TaskContext context) {
        TaskProcessor taskProcessor = context.getTaskProcessor();
        registerTaskHandler(context);
        return null;
    }

    public void registerTaskHandler(TaskContext context) {

        ServiceLoader<TaskExecuteHandler> loader = ServiceLoader.load(TaskExecuteHandler.class);
        loader.forEach(taskExecuteHandler -> {
            boolean hasAnnotation = taskExecuteHandler.getClass().isAnnotationPresent(EnabledTaskHandler.class);
            if (hasAnnotation) {
                EnabledTaskHandler clazzAnnotation = taskExecuteHandler.getClass().getAnnotation(EnabledTaskHandler.class);
                String taskGroup = clazzAnnotation.taskGroup();
                try {
                    taskExecuteHandlerMap.put(taskGroup, taskExecuteHandler.getClass().newInstance());
                    executeHandlerKeyList.add(taskGroup);
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}