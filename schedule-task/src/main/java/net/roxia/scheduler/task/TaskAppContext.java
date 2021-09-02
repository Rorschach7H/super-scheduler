package net.roxia.scheduler.task;

import net.roxia.scheduler.AppContext;
import net.roxia.scheduler.SchedulerConfig;
import net.roxia.scheduler.factory.DefaultServiceFactory;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    /**
     * 连接数据库的数据源
     */
    private DataSource dataSource;


    public Map<String, Runnable> runnerLinkedMap = new HashMap<>();
    public List<String> executeHandlerAlisList = new ArrayList<>();

    public TaskAppContext(SchedulerConfig config) {
        this.config = config;
    }

    @Override
    public SchedulerConfig getConfig() {
        return config;
    }

    public void setConfig(SchedulerConfig config) {
        this.config = config;
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
