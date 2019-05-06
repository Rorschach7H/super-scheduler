package com.meixiaoxi.scheduler.task;

import java.util.Date;

public interface TriggerContext {

    Date lastScheduledExecutionTime();

    Date lastActualExecutionTime();

    Date lastCompletionTime();

}
