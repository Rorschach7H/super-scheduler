package net.roxia.scheduler.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import net.roxia.scheduler.common.utils.JsonUtil;
import net.roxia.scheduler.global.DefaultIdGenerator;
import net.roxia.scheduler.global.IdGenerator;
import net.roxia.scheduler.message.MsgVersion;
import net.roxia.scheduler.message.body.ClientMsg;
import net.roxia.scheduler.message.protobuf.Header;
import net.roxia.scheduler.message.protobuf.Message;
import net.roxia.scheduler.message.protobuf.MessageType;
import net.roxia.scheduler.message.protobuf.MessageVersion;
import org.apache.commons.lang3.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;

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

    public static String machineId;

    private final ClientConfig config;

    private final IdGenerator idGenerator;

    public Client(ClientConfig config) {
        this.config = config;
        this.idGenerator = new DefaultIdGenerator(1);
        Client.machineId = Long.toHexString(idGenerator.getUID());
    }

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
    public void regClient(ChannelHandlerContext ctx) throws UnknownHostException {

        String requestId = MessageType.CONNECT_CLIENT.name() + "_" + Long.toHexString(idGenerator.getUID());
        InetAddress address = InetAddress.getLocalHost();
        Header header = Header.newBuilder()
                .setVersion(MsgVersion.VERSION_1.getValue())
                .setAccessKey(config.getAccessKey())
                .setGroup(config.getGroup())
                .setType(MessageType.CONNECT_CLIENT)
                .setMachineId(machineId)
                .setRequestId(StringUtils.lowerCase(requestId))
                .setTimestamp(System.currentTimeMillis())
                .build();

        ClientMsg client = ClientMsg.builder()
                .accessKey(config.getAccessKey())
                .group(config.getGroup())
                .machineId(header.getMachineId())
                .ip(address.getHostAddress())
                .build();

        Message message = Message.newBuilder()
                .setHeader(header)
                .setBody(JsonUtil.obj2String(client))
                .build();

        ctx.writeAndFlush(message);
    }
}
