package com.meixiaoxi.scheduler.task;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: CronTask
 * @Description: cron表达式触发器任务类
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-06 09:45:17
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-06    meixiaoxi       v1.0.0           创建
 */
public class CronTask extends TriggerTask {

    private final String expression;

    public CronTask(Runnable runnable, String expression) {
        this(runnable, new CronTrigger(expression));
    }

    public CronTask(Runnable runnable, CronTrigger cronTrigger) {
        super(runnable, cronTrigger);
        this.expression = cronTrigger.getExpression();
    }

    public String getExpression() {
        return expression;
    }
}
