package com.ease.archiecture;

import com.ease.archiecture.framework.*;
import com.ease.archiecture.http.HttpServer;
import com.ease.archiecture.impl.HelloServiceImpl;
import com.ease.archiecture.netty.NettyServer;

import java.util.ServiceLoader;

/**
 * @author wei.jiang
 * @since 19-8-23 上午11:21
 */
public class Provider {

    public static void main(String[] args) throws Exception {
        //1.本地注册
        //{服务名：实现类}
        System.out.println(HelloService.class.getName() + "========" + HelloServiceImpl.class);
        LocalRegister.register(HelloService.class.getName(), HelloServiceImpl.class);

        //2.远程注册
        //{服务名：List(url)}
        URL url = new URL("localhost", 8080);
        RemoteRegister.register(HelloService.class.getName(), url);

        //3.启动tomcat
        Prot httpServer = ProtFactory.getProt();
        httpServer.start(url);
    }
}
