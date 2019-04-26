package com.meixiaoxi.scheduler.core.context;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: TaskBeanDefinition
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-04-25 17:25:09
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-04-25    meixiaoxi       v1.0.0           创建
 */
public interface TaskBeanDefinition {
    public String getClassName();

    public Class getClassType();

    public Object getInstance();
}
