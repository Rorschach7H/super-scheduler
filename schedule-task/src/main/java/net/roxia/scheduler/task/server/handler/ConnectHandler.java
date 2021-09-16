package net.roxia.scheduler.task.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import net.roxia.scheduler.message.protobuf.Header;
import net.roxia.scheduler.message.protobuf.Message;
import net.roxia.scheduler.message.protobuf.MessageType;
import net.roxia.scheduler.task.TaskAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName ConnectHandler
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/16 18:01
 **/
public class ConnectHandler extends ChannelInboundHandlerAdapter {
    private final static Logger log = LoggerFactory.getLogger(ConnectHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("==>channelActive<==");
        String machineId = TaskAppContext.getIdGenerator("machine").getIdString();
        TaskAppContext.getTaskAppContext().getClientContext().putChannelHandlerContext(machineId, ctx);
        Header header = Header.newBuilder().setType(MessageType.CONNECT).setMachineId(machineId).build();
        ctx.channel().attr(AttributeKey.newInstance("machineId")).set(machineId);

        ctx.writeAndFlush(Message.newBuilder().setHeader(header).build());

        super.channelActive(ctx);
    }
}
