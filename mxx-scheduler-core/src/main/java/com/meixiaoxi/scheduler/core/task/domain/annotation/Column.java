package com.meixiaoxi.scheduler.core.task.domain.annotation;

import java.lang.annotation.*;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: Field
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-14 10:47:58
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-14    meixiaoxi       v1.0.0           创建
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Column {
    String value() default "";
}
