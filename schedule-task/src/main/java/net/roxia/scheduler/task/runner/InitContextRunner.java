package net.roxia.scheduler.task.runner;

import net.roxia.scheduler.SchedulerConfig;
import net.roxia.scheduler.adapter.OperateAdapter;
import net.roxia.scheduler.constant.ConfigKeys;
import net.roxia.scheduler.core.processor.TaskAddAdapter;
import net.roxia.scheduler.holder.AppContextHolder;
import net.roxia.scheduler.store.datasource.DataSourceFactory;
import net.roxia.scheduler.task.TaskAppContext;
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

    @Override
    protected void run(TaskAppContext context) {
        AppContextHolder.setAppContext(context);
        SchedulerConfig config = context.getConfig();
        log.info("init datasource with config: url={}", config.getProperty(ConfigKeys.JDBC_URL));
        DataSource dataSource = DataSourceFactory.get(config);
        log.info("init transactionManager");
        TransactionManager transactionManager = new TransactionManager(dataSource);
        log.info("take transactionManager into appContext`s aspectInterceptors");
        context.putAspectInterceptors(transactionManager);

        OperateAdapter.initAdapterMap(new TaskAddAdapter());
    }
}
