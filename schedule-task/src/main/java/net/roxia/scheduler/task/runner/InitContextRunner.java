package net.roxia.scheduler.task.runner;

import net.roxia.scheduler.SchedulerConfig;
import net.roxia.scheduler.adapter.OperateAdapter;
import net.roxia.scheduler.task.client.ClientRegAdapter;
import net.roxia.scheduler.core.processor.TaskAddAdapter;
import net.roxia.scheduler.task.AppContextHolder;
import net.roxia.scheduler.persistence.PersistenceContext;
import net.roxia.scheduler.task.TaskAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        AppContextHolder.setPersistenceContext(new PersistenceContext(config));
        OperateAdapter.initAdapterMap(new TaskAddAdapter(), new ClientRegAdapter());
    }
}
