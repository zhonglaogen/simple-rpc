package com.ease.archiecture.framework;

import com.ease.archiecture.http.HttpClient;
import com.ease.archiecture.http.Invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @author wei.jiang
 * @since 19-8-23 下午1:28
 */
public class ProxyFactory {

    public static <T> T getProxy(Class interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                HttpClient httpClient = new HttpClient();
                Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), method.getParameterTypes(), args);
                List<URL> urls = RemoteRegister.get(interfaceClass.getName());
                return httpClient.send(urls.get(0).getHostname(), urls.get(0).getPort(), invocation);
            }
        });
    }
}
