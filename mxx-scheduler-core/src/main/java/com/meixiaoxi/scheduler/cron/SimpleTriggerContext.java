package com.meixiaoxi.scheduler.cron;

import java.util.Date;

public class SimpleTriggerContext implements TriggerContext {

    /**
     * 上次计划任务执行时间
     */
    private volatile Date lastScheduledExecutionTime;

    /**
     * 上次实际任务执行时间
     */
    private volatile Date lastActualExecutionTime;

    /**
     * 上次任务完成时间
     */
    private volatile Date lastCompletionTime;


    public SimpleTriggerContext() {
    }

    public SimpleTriggerContext(Date lastScheduledExecutionTime, Date lastActualExecutionTime, Date lastCompletionTime) {
        this.lastScheduledExecutionTime = lastScheduledExecutionTime;
        this.lastActualExecutionTime = lastActualExecutionTime;
        this.lastCompletionTime = lastCompletionTime;
    }

    public void update(Date lastScheduledExecutionTime, Date lastActualExecutionTime, Date lastCompletionTime) {
        this.lastScheduledExecutionTime = lastScheduledExecutionTime;
        this.lastActualExecutionTime = lastActualExecutionTime;
        this.lastCompletionTime = lastCompletionTime;
    }


    @Override
    public Date lastScheduledExecutionTime() {
        return this.lastScheduledExecutionTime;
    }

    @Override
    public Date lastActualExecutionTime() {
        return this.lastActualExecutionTime;
    }

    @Override
    public Date lastCompletionTime() {
        return this.lastCompletionTime;
    }

}
