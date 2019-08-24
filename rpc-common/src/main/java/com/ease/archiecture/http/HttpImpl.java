package com.ease.archiecture.http;

import com.ease.archiecture.framework.Prot;
import com.ease.archiecture.framework.URL;

public class HttpImpl implements Prot {
    @Override
    public void start(URL url) {
        new HttpServer().start(url.getHostname(), url.getPort());
    }

    @Override
    public Object send(URL url, Invocation invocation) {
        return new HttpClient().send(url.getHostname(), url.getPort(), invocation);
    }
}
