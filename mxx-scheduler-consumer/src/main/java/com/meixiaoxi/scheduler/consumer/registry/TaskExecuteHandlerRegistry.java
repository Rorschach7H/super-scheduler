package com.meixiaoxi.scheduler.consumer.registry;

import com.meixiaoxi.scheduler.handler.TaskExecuteHandler;
import com.meixiaoxi.scheduler.consumer.annotation.EnabledTaskHandler;
import org.springframework.beans.factory.InitializingBean;

import java.util.*;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: TaskExecuteHandlerRegitry
 * @Description: 任务处理注册器
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-03-05 11:08:03
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-03-05    meixiaoxi       v1.0.0           创建
 */
public class TaskExecuteHandlerRegistry implements InitializingBean {

    public static Map<String, TaskExecuteHandler> taskExecuteHandlerMap = new HashMap<>();
    public static List<String> executeHandlerKeyList = new ArrayList<>();

    @Override
    public void afterPropertiesSet() {
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