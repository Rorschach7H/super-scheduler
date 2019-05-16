package com.meixiaoxi.scheduler.task.runner;

import com.meixiaoxi.scheduler.SchedulerConfig;
import com.meixiaoxi.scheduler.constant.ConfigKeys;
import com.meixiaoxi.scheduler.core.processor.TaskProcessorImpl;
import com.meixiaoxi.scheduler.store.datasource.DataSourceFactory;
import com.meixiaoxi.scheduler.task.TaskAppContext;
import com.meixiaoxi.scheduler.task.TaskCfgLoader;
import com.meixiaoxi.scheduler.transcation.TransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

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

    public InitContextRunner(String cfgPath, String log4jPath) {
        this.config = TaskCfgLoader.load(cfgPath, log4jPath);
    }

    @Override
    protected void run(TaskAppContext context) {
        //初始化bean
        context.proxyInstance(new TaskProcessorImpl(config));
    }

    /**
     * 初始化服务管理器
     *
     * @return
     */
    private TaskAppContext initContext() {
        log.info("init datasource with config: url={}", config.getProperty(ConfigKeys.jdbc_url));
        DataSource dataSource = DataSourceFactory.get(config);
        log.info("init transactionManager");
        TransactionManager transactionManager = new TransactionManager(dataSource);
        log.info("instantiate context with managing all Beans ");
        TaskAppContext context = new TaskAppContext(config);
        log.info("take transactionManager into appContext`s aspectInterceptors");
        context.putAspectInterceptors(transactionManager);
        return context;
    }

    public void start() {
        this.context = initContext();
        start(context);
    }
}
