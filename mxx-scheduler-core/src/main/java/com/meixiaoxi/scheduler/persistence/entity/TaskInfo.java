package com.meixiaoxi.scheduler.persistence.entity;

import java.util.Date;

public class TaskInfo {
    private Long id;

    private String taskName;

    private Long objectId;

    private String taskGroup;

    private Date executeTime;

    private Integer sameExecuteTimeQueue;

    private Byte taskType;

    private Integer failedCount;

    private String remarks;

    private Byte executeState;

    private String executor;

    private Byte status;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getTaskGroup() {
        return taskGroup;
    }

    public void setTaskGroup(String taskGroup) {
        this.taskGroup = taskGroup == null ? null : taskGroup.trim();
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }

    public Integer getSameExecuteTimeQueue() {
        return sameExecuteTimeQueue;
    }

    public void setSameExecuteTimeQueue(Integer sameExecuteTimeQueue) {
        this.sameExecuteTimeQueue = sameExecuteTimeQueue;
    }

    public Byte getTaskType() {
        return taskType;
    }

    public void setTaskType(Byte taskType) {
        this.taskType = taskType;
    }

    public Integer getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(Integer failedCount) {
        this.failedCount = failedCount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Byte getExecuteState() {
        return executeState;
    }

    public void setExecuteState(Byte executeState) {
        this.executeState = executeState;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}