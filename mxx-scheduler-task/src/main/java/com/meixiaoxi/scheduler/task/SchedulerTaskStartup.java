package com.meixiaoxi.scheduler.task;

import com.meixiaoxi.scheduler.SchedulerConfig;
import com.meixiaoxi.scheduler.task.runner.InitContextRunner;
import com.meixiaoxi.scheduler.task.runner.NettyServerRunner;
import com.meixiaoxi.scheduler.task.runner.ScanQueueTaskRunner;
import com.meixiaoxi.scheduler.task.runner.TaskRunner;

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
public class SchedulerTaskStartup {
    public static void main(String[] args) {
        String confPath = null;
        if (args.length > 0) {
            confPath = args[0];
        } else {
            confPath = SchedulerTaskStartup.class.getResource("/").getPath();
        }

        String cfgPath = confPath + "task.properties";
        String log4jPath = confPath + "log4j.properties";

        start(cfgPath, log4jPath);
    }

    private static void start(String cfgPath, String log4jPath) {
        //初始化系统上下文生成器
        InitContextRunner initContextRunner = new InitContextRunner(cfgPath, log4jPath);
        //初始化网络服务器连接启动器
        TaskRunner<TaskAppContext> serverRunner = new NettyServerRunner();
        //初始化任务队列扫描启动器
        TaskRunner<TaskAppContext> taskRunner = new ScanQueueTaskRunner();
        //进行任务启动编排
        initContextRunner.setNext(serverRunner);
        serverRunner.setNext(taskRunner);

        //启动任务链
        initContextRunner.start();
    }
}
