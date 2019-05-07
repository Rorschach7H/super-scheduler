package com.meixiaoxi.scheduler.task;

import com.meixiaoxi.scheduler.SchedulerConfig;

public class Starter {
    public static void main(String[] args) {
        SchedulerConfig config = new SchedulerConfig();
        SchedulerTask task = new SchedulerTask(config);
        task.start();
    }
}
