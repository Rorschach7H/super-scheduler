package com.meixiaoxi.scheduler.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class DefaultServiceFactory implements ServiceFactory {

    private List<AspectInterceptor> aspectInterceptors = new ArrayList<>();
    private Map<Class<?>, Object> classBeanMap = new HashMap<>();
    private Map<String, Object> nameBeanMap = new HashMap<>();
    private Map<String, Object> aliasBeanMap = new HashMap<>();

    public DefaultServiceFactory() {
    }

    @SuppressWarnings("unchecked")
    public <T> T proxyInstance(T t) {
        //方法反射处理回调
        InvocationHandler handler = new DynamicProxy<>(aspectInterceptors, t);
        //通过 类加载器，接口类对象，反射回调，创建代理对象
        T tProxy = (T) Proxy.newProxyInstance(
                handler.getClass().getClassLoader(),
                t.getClass().getInterfaces(),
                handler);
        putBeanMap(t, tProxy);

        return tProxy;
    }

    private void putBeanMap(Object obj, Object objProxy) {
        Class objClass = obj.getClass();
        Class<?>[] interfaces = objClass.getInterfaces();
        for (Class clazz : interfaces) {
            classBeanMap.put(clazz, objProxy);
        }
        nameBeanMap.put(objClass.getName(), objProxy);
    }

    public void setAspectInterceptors(List<AspectInterceptor> aspectInterceptors) {
        this.aspectInterceptors = aspectInterceptors;
    }

    public void putAspectInterceptors(AspectInterceptor aspectInterceptor) {
        this.aspectInterceptors.add(aspectInterceptor);
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> clazz) {
        return (T) classBeanMap.get(clazz);
    }

    @Override
    public Object getBean(String name) {
        return null;
    }

    @Override
    public Class<?> getType(String name) {
        return null;
    }

    @Override
    public boolean containsBean(String name) {
        return false;
    }
}
