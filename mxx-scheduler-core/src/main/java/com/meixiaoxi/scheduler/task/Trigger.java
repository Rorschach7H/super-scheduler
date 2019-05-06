package com.meixiaoxi.scheduler.task;

import java.util.Date;

public interface Trigger {

	/**
	 * 通过触发器上下文，计算出下一个执行时间
	 */
	Date nextExecutionTime(TriggerContext triggerContext);

}
