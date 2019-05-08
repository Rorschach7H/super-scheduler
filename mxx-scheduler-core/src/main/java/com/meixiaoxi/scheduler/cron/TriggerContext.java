package com.meixiaoxi.scheduler.cron;

import java.util.Date;

public interface TriggerContext {

    Date lastScheduledExecutionTime();

    Date lastActualExecutionTime();

    Date lastCompletionTime();

}
