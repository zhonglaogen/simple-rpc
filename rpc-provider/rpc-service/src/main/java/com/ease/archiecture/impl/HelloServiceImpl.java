package com.ease.archiecture.impl;


import com.ease.archiecture.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String username) {
        return "hello, " + username;
    }
}
