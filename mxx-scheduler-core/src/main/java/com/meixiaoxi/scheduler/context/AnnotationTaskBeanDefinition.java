package com.meixiaoxi.scheduler.context;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: AnnotationTaskBeanDefinition
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-04-25 17:45:55
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-04-25    meixiaoxi       v1.0.0           创建
 */
public class AnnotationTaskBeanDefinition implements TaskBeanDefinition {

    private Object instance;
    private String className;

    public AnnotationTaskBeanDefinition(Object instance, String className) {
        this.instance = instance;
        this.className = className;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public Class getClassType() {
        return instance.getClass();
    }

    @Override
    public Object getInstance() {
        return instance;
    }
}
