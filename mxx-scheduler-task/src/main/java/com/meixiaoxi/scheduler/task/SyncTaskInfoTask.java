package com.meixiaoxi.scheduler.task;

import com.meixiaoxi.scheduler.core.handler.DefaultTaskPersistenceHandler;
import com.meixiaoxi.scheduler.core.model.Task;
import com.meixiaoxi.scheduler.core.processor.TaskProcessor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title 将未就绪任务加入执行队列
 * @Description
 * @Team 新金融业务研发团队
 * @Author HUANGJUNWEI452
 * @Date 2018/9/12 17:08
 * @Version V1.0
 */
@Component
public class SyncTaskInfoTask {

    @Autowired
    private TaskProcessor taskProcessor;

    @Resource(name = "defaultTaskPersistenceHandler")
    private DefaultTaskPersistenceHandler taskService;

    /**
     * 每五分钟执行一次
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    protected void execute() {
        List<Task> taskList = taskService.getUnReadyTask();
        if (CollectionUtils.isNotEmpty(taskList)) {
            taskProcessor.addUnReadyTaskToQueue(taskList);
        }
    }
}
