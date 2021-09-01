package net.roxia.scheduler.transcation;

import java.lang.annotation.*;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: Transactional
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-15 14:54:08
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-15    meixiaoxi       v1.0.0           创建
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface Transactional {
}
