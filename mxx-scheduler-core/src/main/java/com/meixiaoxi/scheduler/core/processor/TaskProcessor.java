package com.meixiaoxi.scheduler.core.processor;

import com.meixiaoxi.scheduler.core.handler.TaskExecuteHandler;
import com.meixiaoxi.scheduler.core.task.domain.TaskPo;

import java.util.List;

/**
 * @Title 任务处理引擎接口
 * @Description
 * @Team 新金融业务研发团队
 * @Author HUANGJUNWEI452
 * @Date 2018/8/27 17:35
 * @Version V1.0
 */
public interface TaskProcessor {

    /**
     * 添加任务
     *
     * @param taskInfo
     * @return
     */
    boolean addTask(TaskPo taskInfo);

    /**
     * 删除任务
     *
     * @param objectId
     * @return
     */
    boolean removeTask(Long objectId);

    /**
     * 执行任务
     *
     * @param taskGroup
     * @param handler
     */
    List<TaskPo> executeTask(String taskGroup, TaskExecuteHandler handler);

    /**
     * 添加未就绪任务为等待执行
     *
     * @param taskList
     */
    void addUnReadyTaskToQueue(List<TaskPo> taskList);
}
