package net.roxia.scheduler.task.server.handler;

import io.netty.channel.ChannelHandlerContext;
import net.roxia.scheduler.handler.AbstractHandler;
import net.roxia.scheduler.handler.MessageHandlerProcessor;
import net.roxia.scheduler.message.protobuf.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BizMessageHandler extends AbstractHandler {

    private final static Logger log = LoggerFactory.getLogger(BizMessageHandler.class);

    @Override
    protected boolean handleMsg(ChannelHandlerContext ctx, Message message) {
        log.info("==>channelRead<== | \n{}", message.toString());
        MessageHandlerProcessor.assignmentMsg(message);
        return true;
    }
}