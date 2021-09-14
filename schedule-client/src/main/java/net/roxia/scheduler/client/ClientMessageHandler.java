package net.roxia.scheduler.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import net.roxia.scheduler.common.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientMessageHandler extends ChannelInboundHandlerAdapter {

    private final static Logger log = LoggerFactory.getLogger(ClientMessageHandler.class);

    private Client client;

    public ClientMessageHandler(Client client) {
        this.client = client;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("---------------{}----------------", "channelRead");
        log.info("received server msg: {}", JsonUtil.obj2String(msg));
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("---------------{}----------------", "channelActive");
        client.regClient(ctx);
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("==>channelInactive<==");
        super.channelInactive(ctx);
    }
}