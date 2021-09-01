package net.roxia.scheduler.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import net.roxia.scheduler.message.Header;
import net.roxia.scheduler.message.Message;
import org.apache.log4j.PropertyConfigurator;

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
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new ClientInitializer());
            Channel channel = bootstrap.connect("localhost", 9088).sync().channel();
            String key = UUID.randomUUID().toString();
            Header header = new Header(1, key);
            header.setName("hello111");
            header.setAlias("world222222");

            Message message = new Message(header, "你好");
            channel.writeAndFlush(message);

        } finally {
            //eventLoopGroup.shutdownGracefully();
        }
    }
}
