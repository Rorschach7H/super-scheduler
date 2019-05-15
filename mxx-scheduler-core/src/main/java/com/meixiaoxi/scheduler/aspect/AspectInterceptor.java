package com.meixiaoxi.scheduler.aspect;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: AspectInterceptor
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-15 17:00:49
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-15    meixiaoxi       v1.0.0           创建
 */
public interface AspectInterceptor {

    public void beforeDo();
    public void afterDo();
}
