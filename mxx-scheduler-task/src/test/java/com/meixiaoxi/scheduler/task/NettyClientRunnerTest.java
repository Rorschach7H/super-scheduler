package com.meixiaoxi.scheduler.task;

import com.meixiaoxi.scheduler.handler.Decoder;
import com.meixiaoxi.scheduler.handler.Encoder;
import com.meixiaoxi.scheduler.message.Header;
import com.meixiaoxi.scheduler.message.Message;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.UUID;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: NettyClientRunnerTest
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-06 17:40:04
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-06    meixiaoxi       v1.0.0           创建
 */
public class NettyClientRunnerTest {
    public static void main(String[] args) throws Exception {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            // 添加编解码器, 由于ByteToMessageDecoder的子类无法使用@Sharable注解,
                            // 这里必须给每个Handler都添加一个独立的Decoder.
                            pipeline.addLast(new Encoder());
                            pipeline.addLast(new Decoder());
                        }
                    });

            Channel channel = bootstrap.connect("localhost", 9088).sync().channel();
            String key = UUID.randomUUID().toString();
            Header header = new Header(1, key);
            header.setName("hello111");
            header.setAlias("world222222");

            Message message = new Message(header, "你好");
            channel.writeAndFlush(message);

        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
