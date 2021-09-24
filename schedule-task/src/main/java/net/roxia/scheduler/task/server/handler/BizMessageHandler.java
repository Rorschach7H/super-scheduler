package net.roxia.scheduler.task.server.handler;

import io.netty.channel.ChannelHandlerContext;
import net.roxia.scheduler.adapter.OperateAdapter;
import net.roxia.scheduler.handler.AbstractHandler;
import net.roxia.scheduler.message.enums.BizMessageType;
import net.roxia.scheduler.message.enums.MsgVersion;
import net.roxia.scheduler.message.protobuf.Header;
import net.roxia.scheduler.message.protobuf.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BizMessageHandler extends AbstractHandler {

    private final static Logger log = LoggerFactory.getLogger(BizMessageHandler.class);

    @Override
    protected boolean handleMsg(ChannelHandlerContext ctx, Message message) {
        log.info("received biz message | \n{}", message.toString());
        Header header = message.getHeader();
        BizMessageType type = BizMessageType.valueOf(header.getBizType());
        String result = OperateAdapter.getOperateAdapter(type).handle(message);

        Header responseHeader = Header.newBuilder(header)
                .setVersion(MsgVersion.VERSION_1.getValue())
                .setTimestamp(System.currentTimeMillis())
                .setBizType(BizMessageType.RESPONSE.name())
                .build();
        Message response = Message.newBuilder()
                .setHeader(responseHeader)
                .setBody(result)
                .build();
        log.info("client message deal result. response: \n{}", response.toString());
        ctx.writeAndFlush(response);
        return true;
    }
}