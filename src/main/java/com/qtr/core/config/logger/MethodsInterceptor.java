package com.qtr.core.config.logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MethodsInterceptor<T> implements InvocationHandler {

    private T t;

    public MethodsInterceptor(T t) {
        this.t = t;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Info : " + method.getName() +" with params: " + t);
        return method.invoke(t, args);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getProxy(T t, Class<? super T> interfaceType) {
        MethodsInterceptor handler = new MethodsInterceptor(t);

        return (T) Proxy.newProxyInstance(interfaceType.getClassLoader(),
                new Class<?>[]{interfaceType}, handler);
    }
}