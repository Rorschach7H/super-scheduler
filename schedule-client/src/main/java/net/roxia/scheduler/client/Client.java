package net.roxia.scheduler.client;

import io.netty.bootstrap.Bootstrap;
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
import net.roxia.scheduler.message.protobuf.MessageCode;
import net.roxia.scheduler.message.protobuf.MessageType;
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

    private static ClientConfig config;

    private static IdGenerator idGenerator;

    public static void init(ClientConfig config) {
        Client.config = config;
        Client.idGenerator = new DefaultIdGenerator(1);
    }

    public static void start() throws InterruptedException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientInitializer());
            bootstrap.connect(config.getHost(), config.getPort()).sync().channel();
        } finally {
            //eventLoopGroup.shutdownGracefully();
        }
    }

    /**
     * 客户端注册
     */
    public static Message regClient() {
        String requestId = MessageType.CONNECT.name() + "_" + Long.toHexString(idGenerator.getId());
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException ignored) {

        }
        Header header = Header.newBuilder()
                .setVersion(MsgVersion.VERSION_1.getValue())
                .setAccessKey(config.getAccessKey())
                .setMachineId(machineId)
                .setCode(MessageCode.HANDSHAKE)
                .setGroup(config.getGroup())
                .setType(MessageType.CONNECT)
                .setRequestId(StringUtils.lowerCase(requestId))
                .setTimestamp(System.currentTimeMillis())
                .build();

        ClientMsg client = ClientMsg.builder()
                .accessKey(config.getAccessKey())
                .group(config.getGroup())
                .machineId(header.getMachineId())
                .ip(address.getHostAddress())
                .build();

        return Message.newBuilder()
                .setHeader(header)
                .setBody(JsonUtil.obj2String(client))
                .build();
    }
}
