package net.roxia.scheduler.task.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;
import net.roxia.scheduler.message.protobuf.Message;
import net.roxia.scheduler.task.server.handler.BizMessageHandler;
import net.roxia.scheduler.task.server.handler.ConnectHandler;
import net.roxia.scheduler.task.server.handler.TokenAuthHandler;

import java.util.concurrent.TimeUnit;

public class ServerMessageInitializer extends ChannelInitializer<SocketChannel> {
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
                .addLast(new IdleStateHandler(10, 0, 0, TimeUnit.SECONDS))
                //连接消息处理
                .addLast(new ConnectHandler())
                //消息体校验
                .addLast(new TokenAuthHandler())
                //处理实体
                .addLast(new BizMessageHandler());
    }
}