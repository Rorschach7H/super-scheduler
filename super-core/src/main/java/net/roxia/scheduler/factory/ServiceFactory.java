package net.roxia.scheduler.factory;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: ServiceFactory
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-16 10:24:37
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-16    meixiaoxi       v1.0.0           创建
 */
public interface ServiceFactory {
    Object getBean(String name);

    <T> T getBean(Class<T> type);

    Class<?> getType(String name);

    boolean containsBean(String name);
}
