package com.meixiaoxi.scheduler.core.task.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: TaskParam
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-04-30 10:09:09
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-04-30    meixiaoxi       v1.0.0           创建
 */
public class TaskQuery {
    /**
     * 任务ID
     */
    private Long id = 0L;

    /**
     * 要执行的对象ID
     */
    private Long objectId;

    /**
     * 任务名
     */
    @JsonIgnore
    private String taskName;

    /**
     * 任务组名
     */
    private String taskGroup;
    /**
     * 执行时间
     */
    private String executeTime;
    /**
     * 任务类型
     */
    private int taskType;
    /**
     * 已失败次数
     */
    private int failedCount;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 执行结果（0-待执行，1-执行成功，2-执行失败）
     */
    private byte executeState;

    /**
     * 同一时间下（精确到秒）任务序列
     */
    private double sameExecuteTimeQueue = 0;

    /**
     * 任务添加人
     */
    private String executor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskGroup() {
        return taskGroup;
    }

    public void setTaskGroup(String taskGroup) {
        this.taskGroup = taskGroup;
    }

    public String getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public int getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(int failedCount) {
        this.failedCount = failedCount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public byte getExecuteState() {
        return executeState;
    }

    public void setExecuteState(byte executeState) {
        this.executeState = executeState;
    }

    public double getSameExecuteTimeQueue() {
        return sameExecuteTimeQueue;
    }

    public void setSameExecuteTimeQueue(double sameExecuteTimeQueue) {
        this.sameExecuteTimeQueue = sameExecuteTimeQueue;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }
}
