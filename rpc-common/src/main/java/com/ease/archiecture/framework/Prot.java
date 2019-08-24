package com.ease.archiecture.framework;

import com.ease.archiecture.http.Invocation;

public interface Prot {

    void start(URL url);

    Object send(URL url, Invocation invocation);
}
