package com.ease.archiecture;


import com.ease.archiecture.http.HttpClient;
import com.ease.archiecture.http.Invocation;

public class Consumer {

    public static void main(String[] args) throws NoSuchMethodException {

        HttpClient httpClient = new HttpClient();
        Invocation invocation = new Invocation(HelloService.class.getName(), "sayHello", new Class[]{String.class}, new Object[]{"world"});
        String result = httpClient.send("localhost", 8080, invocation);
        System.out.println(result);

    }
}
