package net.roxia.scheduler.task.server.handler;

import io.netty.channel.ChannelHandlerContext;
import net.roxia.scheduler.handler.AbstractHandler;
import net.roxia.scheduler.message.protobuf.Header;
import net.roxia.scheduler.message.protobuf.Message;
import net.roxia.scheduler.message.protobuf.MessageCode;
import net.roxia.scheduler.message.protobuf.MessageType;
import net.roxia.scheduler.task.TaskAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @ClassName ConnectHandler
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/16 18:01
 **/
public class ConnectHandler extends AbstractHandler {

    private final static Logger log = LoggerFactory.getLogger(ConnectHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("==>channelActive<==");
        String machineId = TaskAppContext.getIdGenerator("machine").getIdString();
        TaskAppContext.getTaskAppContext().getClientContext().putChannelHandlerContext(machineId, ctx);
        ctx.channel().attr(getMachineIdKey()).set(machineId);
        Header header = Header.newBuilder()
                .setType(MessageType.CONNECT)
                .setCode(MessageCode.HANDSHAKE)
                .setMachineId(machineId)
                .build();

        ctx.writeAndFlush(Message.newBuilder().setHeader(header).build());

        super.channelActive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        String machineId = String.valueOf(ctx.channel().attr(getMachineIdKey()).get());
        if (cause instanceof IOException) {
            log.warn("client [{}] connect closed!", machineId);
            return;
        }
        log.error("client [{}] channel happened error", machineId, cause);
    }

    @Override
    protected boolean handleMsg(ChannelHandlerContext ctx, Message msg) {
        if (msg.getHeader().getCode() == MessageCode.PING) {
            sendPongMsg(ctx);
            return false;
        }
        return true;
    }

    @Override
    protected void handleReaderIdle(ChannelHandlerContext ctx) {
        super.handleReaderIdle(ctx);
        log.warn("client [{}] reader timeout, close it", ctx.channel().attr(getMachineIdKey()).get());
        ctx.close();
    }
}
