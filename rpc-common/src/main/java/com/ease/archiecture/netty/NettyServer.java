package com.ease.archiecture.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NettyServer {


    private EventLoopGroup bossGroup = null;
    private EventLoopGroup workerGroup = null;

    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(16, 16, 600L,
            TimeUnit.SECONDS, new ArrayBlockingQueue<>(65536));

    public void start(String host, int port) throws Exception {
        if (bossGroup == null && workerGroup == null) {
            bossGroup = new NioEventLoopGroup();
            workerGroup = new NioEventLoopGroup();
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline().addLast(new ObjectDecoder(ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
                            channel.pipeline().addLast(new ObjectEncoder());
                            channel.pipeline().addLast(new NettyServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture future = bootstrap.bind(host, port).sync();
            future.channel().closeFuture().sync();
        }
    }
}
