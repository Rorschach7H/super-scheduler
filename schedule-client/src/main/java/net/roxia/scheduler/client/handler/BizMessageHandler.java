package net.roxia.scheduler.client.handler;

import io.netty.channel.ChannelHandlerContext;
import net.roxia.scheduler.handler.AbstractHandler;
import net.roxia.scheduler.message.protobuf.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BizMessageHandler extends AbstractHandler {

    private final static Logger log = LoggerFactory.getLogger(BizMessageHandler.class);

    @Override
    public boolean handleMsg(ChannelHandlerContext ctx, Message msg) {
        log.info("---------------{}----------------", "channelRead");
        return true;
    }
}