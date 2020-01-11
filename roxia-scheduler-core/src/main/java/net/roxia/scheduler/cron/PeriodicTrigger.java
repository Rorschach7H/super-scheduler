package net.roxia.scheduler.cron;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: PeriodicTrigger
 * @Description: 常规周期性触发器
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-06 09:43:53
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-06    meixiaoxi       v1.0.0           创建
 */
public class PeriodicTrigger implements Trigger {

    private final long period;
    private final TimeUnit timeUnit;
    private volatile long initialDelay = 0L;

    public PeriodicTrigger(long period) {
        this(period, null);
    }

    public PeriodicTrigger(long period, TimeUnit timeUnit) {
        if (period <= 0) {
            throw new RuntimeException("period不可为负数");
        }
        this.timeUnit = (timeUnit != null ? timeUnit : TimeUnit.MILLISECONDS);
        this.period = this.timeUnit.toMillis(period);
    }

    @Override
    public Date nextExecutionTime(TriggerContext triggerContext) {
        Date lastExecution = triggerContext.lastScheduledExecutionTime();
        Date lastCompletion = triggerContext.lastCompletionTime();
        if (lastExecution == null || lastCompletion == null) {
            return new Date(System.currentTimeMillis() + this.initialDelay);
        }
        return new Date(lastExecution.getTime() + this.period);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PeriodicTrigger)) {
            return false;
        }
        PeriodicTrigger otherTrigger = (PeriodicTrigger) other;
        return (this.initialDelay == otherTrigger.initialDelay && this.period == otherTrigger.period);
    }

    @Override
    public int hashCode() {
        return (int) (37 * this.period) + (int) (41 * this.initialDelay);
    }
}
