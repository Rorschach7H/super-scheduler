package com.meixiaoxi.scheduler.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class DynamicProxy<T> implements InvocationHandler {

    private List<AspectInterceptor> aspectInterceptors;

    //这里是泛型，通配型很强
    private T object;

    public DynamicProxy(T object) {
        this.object = object;
    }

    public DynamicProxy(List<AspectInterceptor> aspectInterceptors, T object) {
        this.aspectInterceptors = aspectInterceptors;
        this.object = object;
    }

    public Object invoke(Object proxy, Method method, Object[] args) {
        //反射
        aspectInterceptors.forEach(AspectInterceptor::beforeDo);
        Object obj;
        try {
            obj = method.invoke(object, args);
        } catch (Exception e) {
            aspectInterceptors.forEach(AspectInterceptor::exceptDo);
            throw new RuntimeException(e);
        }
        aspectInterceptors.forEach(AspectInterceptor::afterDo);
        return obj;
    }
}