package com.meixiaoxi.scheduler.task.registry;

import com.meixiaoxi.scheduler.task.ScheduledTask;
import com.meixiaoxi.scheduler.task.Trigger;
import com.meixiaoxi.scheduler.task.TriggerTask;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: TaskReistrar
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-06 14:33:37
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-06    meixiaoxi       v1.0.0           创建
 */
public class TaskReistrar {

    /**
     * 本地线程池
     */
    private ScheduledExecutorService localExecutor = new ScheduledThreadPoolExecutor(100);
    /**
     * 触发器任务集合
     */
    private List<TriggerTask> triggerTasks;
    /**
     * 任务计划集合
     */
    private Set<ScheduledTask> scheduledTasks = new LinkedHashSet<>(32);

    public void setTriggerTasks(List<TriggerTask> triggerTasks) {
        this.triggerTasks = triggerTasks;
    }

    public void setScheduledTasks(Set<ScheduledTask> scheduledTasks) {
        this.scheduledTasks = scheduledTasks;
    }

    public void addTriggerTask(Runnable task, Trigger trigger) {
        addTriggerTask(new TriggerTask(task, trigger));
    }

    public void addTriggerTask(TriggerTask task) {
        if (this.triggerTasks == null) {
            this.triggerTasks = new ArrayList<>();
        }
        this.triggerTasks.add(task);
    }
}
