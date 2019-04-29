package com.meixiaoxi.scheduler.consumer.annotation;

import java.lang.annotation.*;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: EnabledTaskHandler
 * @Description: 用于标注当前类为任务处理器， 该类必须标注在实现 #com.meixiaoxi.scheduler.core.handler.TaskExecuteHandler接口上的类
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-03-05 11:18:02
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-03-05    meixiaoxi       v1.0.0           创建
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface EnabledTaskHandler {
    /**
     * 任务处理器对应的任务组名
     *
     * @return
     */
    String taskGroup();
}
