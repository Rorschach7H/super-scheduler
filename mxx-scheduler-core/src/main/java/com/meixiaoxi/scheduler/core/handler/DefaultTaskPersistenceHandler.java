package com.meixiaoxi.scheduler.core.handler;

import com.meixiaoxi.scheduler.core.common.DateUtil;
import com.meixiaoxi.scheduler.core.common.TaskConstUtil;
import com.meixiaoxi.scheduler.core.model.Task;
import com.meixiaoxi.scheduler.core.persistence.TaskInfoMapper;
import com.meixiaoxi.scheduler.core.persistence.entity.TaskInfo;
import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: DefaultTaskPersistenceHandler
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-03-05 09:34:41
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-03-05    meixiaoxi       v1.0.0           创建
 */
public class DefaultTaskPersistenceHandler implements TaskPersistenceHandler {
    private TaskInfoMapper taskInfoMapper;

    @Override
    public boolean add(Task task) {
        TaskInfo taskInfo = getTaskInfo(task);
        int result = taskInfoMapper.insertSelective(taskInfo);
        if (result > 0) {
            task.setId(taskInfo.getId());
            return true;
        }
        return false;
    }

    @Override
    public boolean add(List<Task> taskList) {
        return false;
    }

    @Override
    public boolean update(Task task) {
        TaskInfo taskInfo = taskInfoMapper.selectByPrimaryKey(task.getId());
        if (taskInfo != null) {
            copyTaskInfo(task, taskInfo);
            return taskInfoMapper.updateByPrimaryKeySelective(taskInfo) == 1;
        }
        return false;
    }

    @Override
    public boolean update(List<Task> taskList) {
        return false;
    }

    @Override
    public int delete(Long taskId) {
        return 0;
    }

    @Override
    public Task get(Long taskId) {
        TaskInfo taskInfo = taskInfoMapper.selectByPrimaryKey(taskId);
        return taskInfo == null ? null : getTask(taskInfo);
    }

    @Override
    public List<Task> get(String taskGroup, String executeDate, long objectId) {
        List<TaskInfo> taskInfoList = taskInfoMapper.selectByObjectId(taskGroup, executeDate, objectId);
        if (CollectionUtils.isNotEmpty(taskInfoList)) {
            return taskInfoList.stream().map(this::getTask).collect(Collectors.toList());
        }
        return new ArrayList();
    }

    @Override
    public List<Task> getUnReadyTask() {
        List<TaskInfo> taskInfoList = taskInfoMapper.selectAbnormalTask();
        return taskInfoList.stream().map(this::getTask).collect(Collectors.toList());
    }

    private TaskInfo getTaskInfo(Task task) {
        TaskInfo taskInfo = new TaskInfo();
        Integer queue = taskInfoMapper.getMaxQueue(task.getTaskGroup(), task.getExecuteTime());
        task.setSameExecuteTimeQueue(queue == null ? 1 : (queue + 1));
        taskInfo.setSameExecuteTimeQueue(new BigDecimal(task.getSameExecuteTimeQueue()).intValue());
        taskInfo.setExecuteState(Task.ExecuteState.WAIT_EXECUTE.getCode());
        taskInfo.setExecuteTime(DateUtil.stringToDate(task.getExecuteTime(), TaskConstUtil.TIME_YYYYMMDDHHMMSS));
        taskInfo.setExecuteState(task.getExecuteState());
        taskInfo.setTaskType((byte) task.getTaskType());
        taskInfo.setObjectId(task.getObjectId());
        taskInfo.setTaskGroup(task.getTaskGroup());
        taskInfo.setRemarks(task.getRemarks());
        taskInfo.setTaskName(task.getTaskName());
        taskInfo.setExecutor(task.getExecutor());
        return taskInfo;
    }

    private void copyTaskInfo(Task task, TaskInfo taskInfo) {
        taskInfo.setExecuteTime(DateUtil.stringToDate(task.getExecuteTime(), TaskConstUtil.TIME_YYYYMMDDHHMMSS));
        taskInfo.setExecuteState(task.getExecuteState());
        taskInfo.setFailedCount(task.getFailedCount());
        taskInfo.setRemarks(task.getRemarks());
    }

    private Task getTask(TaskInfo taskInfo) {
        Task task = new Task();
        task.setId(taskInfo.getId());
        task.setExecuteState(taskInfo.getExecuteState());
        task.setFailedCount(taskInfo.getFailedCount());
        task.setExecuteTime(DateUtil.dateToString(taskInfo.getExecuteTime(), TaskConstUtil.TIME_YYYYMMDDHHMMSS));
        task.setTaskType(taskInfo.getTaskType());
        task.setObjectId(taskInfo.getObjectId());
        task.setTaskGroup(taskInfo.getTaskGroup());
        task.setRemarks(taskInfo.getRemarks());
        task.setTaskName(taskInfo.getTaskName());
        task.setSameExecuteTimeQueue(taskInfo.getSameExecuteTimeQueue());
        task.setExecutor(taskInfo.getExecutor());
        return task;
    }
}
