package net.roxia.scheduler;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: SchedulerContext
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-04-25 14:43:22
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-04-25    meixiaoxi       v1.0.0           创建
 */
public interface AppContext {

    public SchedulerConfig getConfig();

    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> clazz);

    public Object getBean(String name);

    public Class<?> getType(String name);

    public boolean containsBean(String name);
}
