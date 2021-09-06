package net.roxia.scheduler.message.body;

import lombok.*;

/**
 * @ClassName TaskRegInfo
 * @Description 注册任务类
 * @Author huangjunwei01
 * @Date 2021/9/6 15:40
 **/
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class TaskInfo {
    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 任务名
     */
    private String taskName;

    /**
     * 任务详细描述
     */
    private String taskDesc;

    /**
     * 定时任务cron表达式
     */
    private String cron;

    /**
     * 允许最大失败次数
     */
    private Integer allowMaxFailures;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public Integer getAllowMaxFailures() {
        return allowMaxFailures;
    }

    public void setAllowMaxFailures(Integer allowMaxFailures) {
        this.allowMaxFailures = allowMaxFailures;
    }
}
