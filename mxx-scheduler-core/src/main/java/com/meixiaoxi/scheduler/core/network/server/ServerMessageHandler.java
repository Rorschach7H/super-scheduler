package com.meixiaoxi.scheduler.core.network.server;

import com.meixiaoxi.scheduler.core.network.message.MxxMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerMessageHandler extends SimpleChannelInboundHandler<MxxMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MxxMessage msg) throws Exception {
        // 简单地打印出server接收到的消息
        System.out.println(msg.toString());
    }
}