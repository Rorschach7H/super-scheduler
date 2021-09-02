package net.roxia.scheduler.task.runner;

import net.roxia.scheduler.SchedulerConfig;
import net.roxia.scheduler.constant.ConfigKeys;
import net.roxia.scheduler.core.processor.TaskProcessorImpl;
import net.roxia.scheduler.store.datasource.DataSourceFactory;
import net.roxia.scheduler.task.TaskAppContext;
import net.roxia.scheduler.task.TaskCfgLoader;
import net.roxia.scheduler.transcation.TransactionManager;
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
        context.proxyInstance(new TaskProcessorImpl());
    }

    /**
     * 初始化服务管理器
     *
     * @return
     */
    private TaskAppContext initContext() {
        log.info("init datasource with config: url={}", config.getProperty(ConfigKeys.JDBC_URL));
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
