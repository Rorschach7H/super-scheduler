package net.roxia.scheduler.client.handler;

import io.netty.channel.ChannelHandlerContext;
import net.roxia.scheduler.client.Client;
import net.roxia.scheduler.handler.AbstractHandler;
import net.roxia.scheduler.message.protobuf.Header;
import net.roxia.scheduler.message.protobuf.Message;
import net.roxia.scheduler.message.protobuf.MessageCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectHandler extends AbstractHandler {

    private final static Logger log = LoggerFactory.getLogger(ConnectHandler.class);

    @Override
    public boolean handleMsg(ChannelHandlerContext ctx, Message message) {
        Header header = message.getHeader();
        if (header.getCode() == MessageCode.PONG) {
            log.info("received server pong message");
            return false;
        }
        if (header.getCode() == MessageCode.HANDSHAKE) {
            log.info("received server handshake message, machineId={}", header.getMachineId());
            Client.machineId = header.getMachineId();
            ctx.writeAndFlush(Client.regClient());
            return false;
        }
        if (header.getCode() == MessageCode.AUTH_FAIL) {
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
        Client.start();
        super.channelInactive(ctx);
    }
}