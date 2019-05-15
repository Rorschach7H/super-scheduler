package com.meixiaoxi.scheduler.task.runner;

import com.meixiaoxi.scheduler.SchedulerConfig;
import com.meixiaoxi.scheduler.aspect.AspectInterceptor;
import com.meixiaoxi.scheduler.aspect.ServiceAspectManager;
import com.meixiaoxi.scheduler.core.processor.TaskProcessor;
import com.meixiaoxi.scheduler.core.processor.TaskProcessorImpl;
import com.meixiaoxi.scheduler.store.SqlTemplateFactory;
import com.meixiaoxi.scheduler.store.datasource.DataSourceFactory;
import com.meixiaoxi.scheduler.task.TaskAppContext;
import com.meixiaoxi.scheduler.task.TaskCfgLoader;
import com.meixiaoxi.scheduler.transcation.TransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: InitContextRunner
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-04-28 14:33:00
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-04-28    meixiaoxi       v1.0.0           创建
 */
public class InitContextRunner extends TaskRunner<TaskAppContext> {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private SchedulerConfig config;
    private String cfgPath;
    private String log4jPath;

    public InitContextRunner(String cfgPath, String log4jPath) {
        this.cfgPath = cfgPath;
        this.log4jPath = log4jPath;
    }

    @Override
    protected void run(TaskAppContext context) {
        this.config = TaskCfgLoader.load(cfgPath, log4jPath);

        TransactionManager transactionManager = new TransactionManager(DataSourceFactory.get(config));
        List<AspectInterceptor> interceptorList = new ArrayList<>();
        interceptorList.add(transactionManager);
        ServiceAspectManager serviceAspectManager = new ServiceAspectManager(interceptorList);
        TaskProcessor taskProcessor = new TaskProcessorImpl(config);
        TaskProcessor taskProcessorProxy = serviceAspectManager.proxyFor(taskProcessor);
        context.setTaskProcessor(taskProcessorProxy);
    }

    public void start() {
        this.context = new TaskAppContext(config);
        start(context);
    }
}
