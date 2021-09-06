package net.roxia.scheduler.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import net.roxia.scheduler.adapter.enums.OperateEnum;
import net.roxia.scheduler.common.utils.JsonUtil;
import net.roxia.scheduler.message.Header;
import net.roxia.scheduler.message.Message;
import net.roxia.scheduler.message.body.ClientInfo;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: Client
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-04-30 17:59:53
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-04-30    meixiaoxi       v1.0.0           创建
 */
public class Client {

    public void start() throws InterruptedException {

        ClientMessageHandler handler = new ClientMessageHandler(this);
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new ClientInitializer(handler));
            Channel channel = bootstrap.connect("localhost", 9088).sync().channel();

        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public void regClient(ChannelHandlerContext ctx) {
        Header header = Header.builder()
                .type(OperateEnum.REG_CLIENT.name())
                .accessKey("328947238947238904")
                .clientName("super-scheduler-client")
                .build();
        ClientInfo clientInfo = ClientInfo.builder()
                .accessKey("328947238947238904")
                .clientId("23894239847328947")
                .clientName("super-scheduler-client")
                .build();
        Message message = new Message(header, JsonUtil.obj2String(clientInfo));
        ctx.writeAndFlush(message);
    }
}
