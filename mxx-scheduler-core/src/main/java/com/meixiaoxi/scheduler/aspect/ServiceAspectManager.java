package com.meixiaoxi.scheduler.aspect;

import com.meixiaoxi.scheduler.transcation.Transactional;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: ServiceAspectManager
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-15 16:54:01
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-15    meixiaoxi       v1.0.0           创建
 */
public class ServiceAspectManager {

    private List<AspectInterceptor> aspectInterceptors = new ArrayList<>();

    public ServiceAspectManager(List<AspectInterceptor> interceptorList) {
        this.aspectInterceptors = interceptorList;
    }

    public <T> T proxyFor(T t) {
        //    方法反射处理回调
        InvocationHandler handler = new DynamicProxy<>(aspectInterceptors, t);

        //通过 类加载器，接口类对象，反射回调，创建代理对象
        return (T) Proxy.newProxyInstance(handler.getClass().getClassLoader(), t.getClass().getInterfaces(), handler);
    }

    public void setAspectInterceptors(List<AspectInterceptor> aspectInterceptors) {
        this.aspectInterceptors = aspectInterceptors;
    }

    public void putAspectInterceptors(AspectInterceptor aspectInterceptor) {
        this.aspectInterceptors.add(aspectInterceptor);
    }
}
