package com.ease.archiecture.netty;

import com.ease.archiecture.http.Invocation;
import io.netty.channel.*;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    private volatile Channel channel;

    private Invocation invocation;

    private Object result;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        result = msg;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        this.channel = ctx.channel();

    }


    @Override
    public Object call() {
        final CountDownLatch latch = new CountDownLatch(1);
        channel.writeAndFlush(this.invocation).addListener((ChannelFutureListener) channelFuture -> latch.countDown());
//        channel.writeAndFlush(this.invocation).addListener(new GenericFutureListener<Future<? super Void>>() {
//            @Override
//            public void operationComplete(Future<? super Void> future) throws Exception {
//
//            }
//        })
        try {
            latch.await();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public void setInvocation(Invocation invocation) {
        this.invocation = invocation;
    }
}
