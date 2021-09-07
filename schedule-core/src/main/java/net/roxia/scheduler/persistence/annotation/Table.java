package net.roxia.scheduler.persistence.annotation;

import java.lang.annotation.*;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: Table
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-14 10:49:11
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-14    meixiaoxi       v1.0.0           创建
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Table {
    String value();
}
