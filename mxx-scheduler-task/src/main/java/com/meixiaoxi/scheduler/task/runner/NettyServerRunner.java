package com.meixiaoxi.scheduler.task.runner;

import com.meixiaoxi.scheduler.SchedulerConfig;
import com.meixiaoxi.scheduler.task.TaskAppContext;
import com.meixiaoxi.scheduler.task.network.server.ServerMessageInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: ServerRunner
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-04-26 10:34:38
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-04-26    meixiaoxi       v1.0.0           创建
 */
public class NettyServerRunner extends TaskRunner<TaskAppContext> {

    public NettyServerRunner(TaskAppContext context) {
        this.context = context;
    }

    @Override
    public void run() {
        SchedulerConfig config = null;
        int port = 9088;
        NioEventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(group)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ServerMessageInitializer());
        ChannelFuture future = bootstrap.bind(new InetSocketAddress(port));
        future.addListener((ChannelFutureListener) channelFuture -> {
            if (channelFuture.isSuccess()) {
                System.out.println("Server bound");
            } else {
                System.err.println("Bind attempt failed");
                channelFuture.cause().printStackTrace();
            }
        });
        runNext();
    }
}
