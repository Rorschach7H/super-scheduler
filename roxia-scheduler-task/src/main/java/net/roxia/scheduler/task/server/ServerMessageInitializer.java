package net.roxia.scheduler.task.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import net.roxia.scheduler.handler.Decoder;
import net.roxia.scheduler.handler.Encoder;

public class ServerMessageInitializer extends ChannelInitializer<SocketChannel> {

    private static final Encoder ENCODER = new Encoder();

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {

        ChannelPipeline pipeline = channel.pipeline();

        // 添加编解码器, 由于ByteToMessageDecoder的子类无法使用@Sharable注解,
        // 这里必须给每个Handler都添加一个独立的Decoder.
        pipeline.addLast(ENCODER);
        pipeline.addLast(new Decoder());

        // 添加逻辑控制层
        pipeline.addLast(new ServerMessageHandler());

    }
}