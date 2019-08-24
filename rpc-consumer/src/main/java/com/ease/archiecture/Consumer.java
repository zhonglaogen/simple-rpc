package com.ease.archiecture;


import com.ease.archiecture.framework.ProxyFactory;

public class Consumer {

    public static void main(String[] args) throws NoSuchMethodException {

        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        System.out.println(helloService.sayHello("abc"));
//        HttpClient httpClient=new HttpClient();
//        httpClient.send("localhost",8080, new Invocation());

    }
}
