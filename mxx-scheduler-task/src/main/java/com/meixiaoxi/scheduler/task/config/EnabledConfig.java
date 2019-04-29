package com.meixiaoxi.scheduler.task.config;

import java.lang.annotation.*;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: EnabledConfig
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-04-29 14:26:19
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-04-29    meixiaoxi       v1.0.0           创建
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface EnabledConfig {
    String prefix() default "";
}
