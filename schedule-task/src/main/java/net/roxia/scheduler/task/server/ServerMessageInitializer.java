package net.roxia.scheduler.task.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import net.roxia.scheduler.message.protobuf.ProtoMsg;

public class ServerMessageInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline
                //解码1 根据byte的头长度分割
                .addLast(new ProtobufVarint32FrameDecoder())
                //解码2 byte转化为消息实体
                .addLast(new ProtobufDecoder(ProtoMsg.Message.getDefaultInstance()))
                //编码3 byte数组头加上实体类长度
                .addLast(new ProtobufVarint32LengthFieldPrepender())
                //编码4 处理实体
                .addLast(new ServerMessageHandler())
                //编码2 实体转化为byte数组
                .addLast(new ProtobufEncoder());
    }
}