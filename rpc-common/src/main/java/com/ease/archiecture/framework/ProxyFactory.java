package com.ease.archiecture.framework;

import com.ease.archiecture.http.HttpClient;
import com.ease.archiecture.http.Invocation;
import com.ease.archiecture.netty.NettyClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author wei.jiang
 * @since 19-8-23 下午1:28
 */
public class ProxyFactory {

    public static <T> T getProxy(final Class interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, (proxy, method, args) -> {
            HttpClient httpClient = new HttpClient();
            Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), method.getParameterTypes(), args);
            URL url = RemoteRegister.get(interfaceClass.getName());
            return httpClient.send(url.getHostname(), url.getPort(), invocation);
        });
    }
}
