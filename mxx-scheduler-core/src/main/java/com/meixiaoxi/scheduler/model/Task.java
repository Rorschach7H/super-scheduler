package com.meixiaoxi.scheduler.model;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @Title
 * @Description
 * @Team 新金融业务研发团队
 * @Author HUANGJUNWEI452
 * @Date 2018/7/6 10:14
 * @Version V1.0
 */
public class Task {

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
    @JsonIgnore
    private String remarks;

    /**
     * 执行结果（0-待执行，1-执行成功，2-执行失败）
     */
    @JsonIgnore
    private byte executeState;

    /**
     * 同一时间下（精确到秒）任务序列
     */
    private double sameExecuteTimeQueue = 0;

    /**
     * 任务添加人
     */
    private String executor;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public enum ExecuteState {

        UN_READY((byte) -1, "未就绪"),
        WAIT_EXECUTE((byte) 0, "待执行"),
        SUCCESS((byte) 1, "执行成功"),
        FAIL((byte) 2, "执行失败"),
        CANCEL((byte) 3, "任务取消");

        private byte code;
        private String name;

        ExecuteState(byte code, String name) {
            this.code = code;
            this.name = name;
        }

        public byte getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }

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