package net.roxia.scheduler.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import net.roxia.scheduler.common.utils.JsonUtil;
import net.roxia.scheduler.global.IdGenerator;
import net.roxia.scheduler.holder.IdGenerateHolder;
import net.roxia.scheduler.message.body.ClientMsg;
import net.roxia.scheduler.message.enums.MsgVersion;
import net.roxia.scheduler.message.enums.SysMessageType;
import net.roxia.scheduler.message.protobuf.Header;
import net.roxia.scheduler.message.protobuf.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

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

    private final AtomicInteger retryCount = new AtomicInteger();

    private final Logger log = LoggerFactory.getLogger(Client.class);

    private String machineId;

    private ClientConfig config;

    private IdGenerator idGenerator;

    private final EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

    private static Client client;

    private Client() {
    }

    public void start() {
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ClientInitializer());
            bootstrap.connect(config.getHost(), config.getPort()).addListener((ChannelFutureListener) future -> {
                if (!future.isSuccess()) {
                    retryCount.incrementAndGet();
                    final EventLoop loop = future.channel().eventLoop();
                    loop.schedule(client::start, 1L, TimeUnit.SECONDS);
                }
            }).sync().channel();
            retryCount.set(0);
        } catch (Exception e) {
            int count = retryCount.get();
            if (count <= 10) {
                log.warn("client connect server fail, and try again. retry_count={}", count);
            } else {
                log.error("client connect server fail, please check the network or server available or not.", e);
            }
        }
    }

    /**
     * 客户端注册
     */
    public Message regClient() {
        String requestId = IdGenerateHolder
                .generateRequestId(SysMessageType.CONNECT, idGenerator.getIdString());
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            throw new RuntimeException(ex);
        }
        Header header = Header.newBuilder()
                .setVersion(MsgVersion.VERSION_1.getValue())
                .setAccessKey(config.getAccessKey())
                .setMachineId(machineId)
                .setSysType(SysMessageType.CONNECT.name())
                .setGroup(config.getGroup())
                .setRequestId(requestId)
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

    public static Client init(ClientConfig config, IdGenerator idGenerator) {
        client = new Client();
        client.config = config;
        client.idGenerator = idGenerator;
        return client;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public ClientConfig getConfig() {
        return config;
    }

    public IdGenerator getIdGenerator() {
        return idGenerator;
    }

    public static Client getClient() {
        return client;
    }
}
