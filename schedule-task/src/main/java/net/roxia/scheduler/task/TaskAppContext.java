package net.roxia.scheduler.task;

import net.roxia.scheduler.AppContext;
import net.roxia.scheduler.SchedulerConfig;
import net.roxia.scheduler.factory.DefaultServiceFactory;
import net.roxia.scheduler.task.client.ClientContext;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: ConsumerTaskContext
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-04-25 16:35:33
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-04-25    meixiaoxi       v1.0.0           创建
 */
public class TaskAppContext extends DefaultServiceFactory implements AppContext {
    /**
     * 系统配置
     */
    private SchedulerConfig config;

    private ClientContext clientContext;

    public TaskAppContext(SchedulerConfig config) {
        this.clientContext = new ClientContext();
        this.config = config;
    }

    @Override
    public SchedulerConfig getConfig() {
        return config;
    }

    public void setConfig(SchedulerConfig config) {
        this.config = config;
    }

    public ClientContext getClientContext() {
        return clientContext;
    }

    public void setClientContext(ClientContext clientContext) {
        this.clientContext = clientContext;
    }
}
