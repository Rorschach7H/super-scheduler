package net.roxia.scheduler.client;

import com.google.protobuf.Any;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import net.roxia.scheduler.message.protobuf.ProtoBody;
import net.roxia.scheduler.message.protobuf.ProtoMsg;

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

    private ClientConfig config;

    public void start() throws InterruptedException {

        ClientMessageHandler readHandler = new ClientMessageHandler(this);
        ClientWriteHandler writeHandler = new ClientWriteHandler(this);
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientInitializer(readHandler, writeHandler));
            bootstrap.connect(config.getHost(), config.getPort()).sync().channel();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    /**
     * 客户端注册
     *
     * @param ctx
     */
    public void regClient(ChannelHandlerContext ctx) {

        ProtoMsg.Header header = ProtoMsg.Header.newBuilder()
                .setVersion("1.0")
                .setAccessKey(config.getAccessKey())
                .setGroup(config.getGroup())
                .setType(ProtoMsg.MessageType.REG_CLIENT)
                .build();

        ProtoBody.Client client = ProtoBody.Client.newBuilder()
                .setAccessKey(config.getAccessKey())
                .setGroup(config.getGroup())
                .build();

        ProtoMsg.Message message = ProtoMsg.Message.newBuilder()
                .setHeader(header)
                .setBody(Any.pack(client))
                .build();

        ctx.writeAndFlush(message);
    }

    public Client(ClientConfig config) {
        this.config = config;
    }
}
