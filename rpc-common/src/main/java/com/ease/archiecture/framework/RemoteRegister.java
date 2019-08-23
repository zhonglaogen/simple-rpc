package com.ease.archiecture.framework;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RemoteRegister {

    private static Map<String, List<URL>> REGISTER = new HashMap<>();

    public static void register(String interfaceName, URL url) {
        List<URL> urls = Collections.singletonList(url);
        REGISTER.put(interfaceName, urls);
    }

    public static List<URL> get(String interfaceName){
        return REGISTER.get(interfaceName);
    }
}
