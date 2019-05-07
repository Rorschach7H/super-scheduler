package com.meixiaoxi.scheduler.task;

import com.meixiaoxi.scheduler.SchedulerConfig;
import com.meixiaoxi.scheduler.task.runner.InitContextRunner;
import com.meixiaoxi.scheduler.task.runner.NettyServerRunner;
import com.meixiaoxi.scheduler.task.runner.ScanQueueTaskRunner;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: SchedulerTaskRunner
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-04-28 10:14:33
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-04-28    meixiaoxi       v1.0.0           创建
 */
public class SchedulerTask {

    private SchedulerConfig config;

    public SchedulerTask(SchedulerConfig config) {
        this.config = config;
    }

    public void start() {
        //初始化系统上下文生成器
        InitContextRunner initContextRunner = new InitContextRunner(config);
        TaskAppContext context = initContextRunner.getContext();
        //初始化网络服务器连接启动器
        NettyServerRunner serverRunner = new NettyServerRunner(context);
        //初始化任务队列扫描启动器
        ScanQueueTaskRunner taskRunner = new ScanQueueTaskRunner(context);
        //进行任务启动编排
        initContextRunner.setNext(serverRunner);
        serverRunner.setNext(taskRunner);

        //启动任务链
        initContextRunner.start();
    }
}
