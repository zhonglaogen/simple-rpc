package com.ease.archiecture.netty;

import com.ease.archiecture.framework.LocalRegister;
import com.ease.archiecture.http.Invocation;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.lang.reflect.Method;

public class NettyServerHandler extends SimpleChannelInboundHandler<Invocation> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Invocation invocation) throws Exception {
        NettyServer.threadPoolExecutor.execute(() -> {
            try {
                Class implClass = LocalRegister.get(invocation.getInterfaceName());
                Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());
                channelHandlerContext.writeAndFlush(method.invoke(implClass.newInstance(), invocation.getParams()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
