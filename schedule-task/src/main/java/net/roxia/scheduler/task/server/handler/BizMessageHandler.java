package net.roxia.scheduler.task.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import net.roxia.scheduler.handler.MessageHandlerProcessor;
import net.roxia.scheduler.message.protobuf.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BizMessageHandler extends ChannelInboundHandlerAdapter {

    private final static Logger log = LoggerFactory.getLogger(BizMessageHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Message message = (Message) msg;
        log.info("==>channelRead<== | \n{}", message.toString());
        MessageHandlerProcessor.assignmentMsg(message);
        super.channelRead(ctx, msg);
    }
}