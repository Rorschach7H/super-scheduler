package net.roxia.scheduler.core.task.domain;

import net.roxia.scheduler.common.utils.JsonUtil;
import net.roxia.scheduler.core.task.domain.annotation.PrimaryKey;
import net.roxia.scheduler.core.task.domain.annotation.Table;

/**
 * @Title
 * @Description
 * @Team 新金融业务研发团队
 * @Author HUANGJUNWEI452
 * @Date 2018/7/6 10:14
 * @Version V1.0
 */
@Table("ru_executing_task")
public class RunExecutingTask extends AbstractEntity {

    /**
     * 任务ID
     */
    @PrimaryKey
    private Long id = 0L;

    /**
     * 要执行的对象ID
     */
    private Long objectId;

    /**
     * 任务名
     */
    private String taskName;

    /**
     * 任务组key
     */
    private String groupKey;

    /**
     * 定时任务cron表达式
     */
    private String cron;

    /**
     * 定时任务执行周期
     */
    private Integer period;

    /**
     * 定时任务相关时间参数的单位(0|秒,1|分,2|时)
     */
    private Byte timeUnit;

    /**
     * 定时任务开始执行的延时
     */
    private Integer initialDelay;

    /**
     * 接入客户端key
     */
    private String accessKey;

    /**
     * 执行时间
     */
    private String executeTime;

    /**
     * 同一时间下（精确到秒）任务序列
     */
    private Long executeQueue;

    /**
     * 执行任务已失败次数
     */
    private Integer failures;
    /**
     * 最大失败次数
     */
    private Integer maxFailures;
    /**
     * 任务状态
     */
    private Byte executeState;

    /**
     * 创建时间
     */
    private String createTime;

    @Override
    public String toString() {
        return JsonUtil.toJsonString(this);
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

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Byte getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(Byte timeUnit) {
        this.timeUnit = timeUnit;
    }

    public Integer getInitialDelay() {
        return initialDelay;
    }

    public void setInitialDelay(Integer initialDelay) {
        this.initialDelay = initialDelay;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }

    public Long getExecuteQueue() {
        return executeQueue;
    }

    public void setExecuteQueue(Long executeQueue) {
        this.executeQueue = executeQueue;
    }

    public Integer getFailures() {
        return failures;
    }

    public void setFailures(Integer failures) {
        this.failures = failures;
    }

    public Integer getMaxFailures() {
        return maxFailures;
    }

    public void setMaxFailures(Integer maxFailures) {
        this.maxFailures = maxFailures;
    }

    public Byte getExecuteState() {
        return executeState;
    }

    public void setExecuteState(Byte executeState) {
        this.executeState = executeState;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}