package com.meixiaoxi.scheduler.consumer;

import com.meixiaoxi.scheduler.consumer.registry.TaskExecuteHandlerRegistry;
import com.meixiaoxi.scheduler.handler.TaskExecuteHandler;
import com.meixiaoxi.scheduler.processor.TaskProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 扫描当前时间可执行的任务
 *
 * @author MEIXIAOXI
 * @date 2018/06/01
 */
@Component
public class ActivateCasesTask {

    @Autowired
    private TaskProcessor taskProcessor;

    @Scheduled(cron = "0/1 * * * * ?")
    protected void execute() {
        TaskExecuteHandlerRegistry.executeHandlerKeyList.forEach(executeKey -> {
            TaskExecuteHandler executeHandler = TaskExecuteHandlerRegistry.taskExecuteHandlerMap.get(executeKey);
            taskProcessor.executeTask(executeKey, executeHandler);
        });
    }
}
