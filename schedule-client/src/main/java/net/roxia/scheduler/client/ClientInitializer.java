package net.roxia.scheduler.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;
import net.roxia.scheduler.client.handler.BizMessageHandler;
import net.roxia.scheduler.client.handler.ConnectHandler;
import net.roxia.scheduler.message.protobuf.Message;

import java.util.concurrent.TimeUnit;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: ClientInitializer
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-04-26 17:07:05
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-04-26    meixiaoxi       v1.0.0           创建
 */
public class ClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline
                //解码1 根据byte的头长度分割
                .addLast(new ProtobufVarint32FrameDecoder())
                //解码2 byte转化为消息实体
                .addLast(new ProtobufDecoder(Message.getDefaultInstance()))
                //编码1 byte数组头加上实体类长度
                .addLast(new ProtobufVarint32LengthFieldPrepender())
                //编码2 实体转化为byte数组
                .addLast(new ProtobufEncoder())
                //处理实体
                .addLast(new IdleStateHandler(0, 0, 5, TimeUnit.SECONDS))
                .addLast(new ConnectHandler(Client.getClient()))
                .addLast(new BizMessageHandler());
    }
}
