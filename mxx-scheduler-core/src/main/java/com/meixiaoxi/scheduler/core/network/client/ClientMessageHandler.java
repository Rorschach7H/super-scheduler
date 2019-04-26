package com.meixiaoxi.scheduler.core.network.client;

import com.meixiaoxi.scheduler.core.network.message.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientMessageHandler extends SimpleChannelInboundHandler<Message> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        // 简单地打印出client接收到的消息
        System.out.println(msg.toString());
    }
}