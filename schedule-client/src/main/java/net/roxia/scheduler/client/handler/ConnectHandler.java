package net.roxia.scheduler.client.handler;

import io.netty.channel.ChannelHandlerContext;
import net.roxia.scheduler.client.Client;
import net.roxia.scheduler.handler.AbstractHandler;
import net.roxia.scheduler.message.enums.SysMessageType;
import net.roxia.scheduler.message.protobuf.Header;
import net.roxia.scheduler.message.protobuf.Message;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectHandler extends AbstractHandler {

    private final Client client;

    private final static Logger log = LoggerFactory.getLogger(ConnectHandler.class);

    public ConnectHandler(Client client) {
        this.client = client;
    }

    @Override
    public boolean handleMsg(ChannelHandlerContext ctx, Message message) {
        Header header = message.getHeader();
        if (SysMessageType.value(header.getSysType()) == SysMessageType.PONG) {
            log.info("received server pong message");
            return false;
        }
        if (SysMessageType.value(header.getSysType()) == SysMessageType.HANDSHAKE) {
            String machineId = Client.getClient().getMachineId();
            if (StringUtils.isEmpty(machineId)) {
                machineId = header.getMachineId();
            }
            log.info("received server handshake message, machineId={}", machineId);
            client.setMachineId(machineId);
            ctx.writeAndFlush(client.regClient());
            return false;
        }
        if (SysMessageType.value(header.getSysType()) == SysMessageType.AUTH_FAIL) {
            log.info(message.getBody());
            return false;
        }
        return true;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.warn("connect error, {}", cause.getMessage());
    }

    @Override
    protected void handleAllIdle(ChannelHandlerContext ctx) {
        sendPingMsg(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.warn("connect server offline and try reconnect");
        client.start();
        super.channelInactive(ctx);
    }
}