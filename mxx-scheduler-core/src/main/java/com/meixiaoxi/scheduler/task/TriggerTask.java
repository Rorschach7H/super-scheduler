package com.meixiaoxi.scheduler.task;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: TriggerTask
 * @Description: 触发器任务类
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-06 09:39:18
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-06    meixiaoxi       v1.0.0           创建
 */
public class TriggerTask extends Task {

    private final Trigger trigger;

    public TriggerTask(Runnable runnable, Trigger trigger) {
        super(runnable);
        this.trigger = trigger;
    }

    public Trigger getTrigger() {
        return trigger;
    }
}
