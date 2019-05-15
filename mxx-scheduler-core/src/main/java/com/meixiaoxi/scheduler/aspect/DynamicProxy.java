package com.meixiaoxi.scheduler.aspect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class DynamicProxy<T> implements InvocationHandler {

    private List<AspectInterceptor> aspectInterceptors;

    //这里是泛型，通配型很强
    private T proxyObj;

    public DynamicProxy(T proxyObj) {
        this.proxyObj = proxyObj;
    }

    public DynamicProxy(List<AspectInterceptor> aspectInterceptors, T proxyObj) {
        this.aspectInterceptors = aspectInterceptors;
        this.proxyObj = proxyObj;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //反射
        aspectInterceptors.forEach(AspectInterceptor::beforeDo);
        Object obj = method.invoke(proxyObj, args);
        aspectInterceptors.forEach(AspectInterceptor::afterDo);
        return obj;
    }
}