package com.meixiaoxi.scheduler.task.server;

import com.meixiaoxi.scheduler.message.Message;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerMessageHandler extends SimpleChannelInboundHandler<Message> {

    private static Logger log = LoggerFactory.getLogger(ServerMessageHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        Channel channel = ctx.channel();
        // 简单地打印出server接收到的消息
        System.out.println(msg.toString());
    }

    @Override
    public boolean acceptInboundMessage(Object msg) throws Exception {
        log.info("---------------{}----------------", "acceptInboundMessage");
        return super.acceptInboundMessage(msg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("---------------{}----------------", "channelRead");
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.info("---------------{}----------------", "channelRegistered");
        Channel channel = ctx.channel();

        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        log.info("---------------{}----------------", "channelUnregistered");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("---------------{}----------------", "channelActive");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("---------------{}----------------", "channelInactive");
        super.channelInactive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("---------------{}----------------", "channelReadComplete");
        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        log.info("---------------{}----------------", "userEventTriggered");
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        log.info("---------------{}----------------", "channelWritabilityChanged");
        super.channelWritabilityChanged(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("---------------{}----------------", "exceptionCaught");
        super.exceptionCaught(ctx, cause);
    }

    @Override
    protected void ensureNotSharable() {
        log.info("---------------{}----------------", "ensureNotSharable");
        super.ensureNotSharable();
    }

    @Override
    public boolean isSharable() {
        log.info("---------------{}----------------", "isSharable");
        return super.isSharable();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("---------------{}----------------", "handlerAdded");
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("---------------{}----------------", "handlerRemoved");
        super.handlerRemoved(ctx);
    }
}