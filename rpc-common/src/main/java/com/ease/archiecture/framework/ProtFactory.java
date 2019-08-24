package com.ease.archiecture.framework;

import java.util.ServiceLoader;

public class ProtFactory {


    public static Prot getProt() {
        ServiceLoader<Prot> serviceLoader = ServiceLoader.load(Prot.class);
        return serviceLoader.iterator().next();
    }

//    public static Prot getProt2() {
//        String prot = System.getProperty("prot");
//        switch (prot) {
//            case "http":
//                ///
//                break;
//            case "netty":
//                //
//                break;
//            default:
//                break;
//        }
//    }
}
