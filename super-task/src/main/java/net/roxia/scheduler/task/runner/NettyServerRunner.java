package net.roxia.scheduler.task.runner;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import net.roxia.scheduler.common.utils.JsonUtil;
import net.roxia.scheduler.task.TaskAppContext;
import net.roxia.scheduler.task.server.ServerMessageInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private Logger log = LoggerFactory.getLogger(NettyServerRunner.class);

    @Override
    protected void run(TaskAppContext context) {
        log.info("nettyServerRunner start...");
        log.info("context: {}", JsonUtil.toJsonString(context));
        int port = 9088;
        NioEventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(group)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ServerMessageInitializer());
        ChannelFuture future = bootstrap.bind(new InetSocketAddress(port));
        future.addListener((ChannelFutureListener) channelFuture -> {
            if (channelFuture.isSuccess()) {
            } else {
                System.err.println("Bind attempt failed");
                channelFuture.cause().printStackTrace();
            }
        });
    }
}
