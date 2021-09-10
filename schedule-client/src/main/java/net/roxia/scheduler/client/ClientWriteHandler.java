package net.roxia.scheduler.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientWriteHandler extends ChannelOutboundHandlerAdapter {

    private final static Logger log = LoggerFactory.getLogger(ClientWriteHandler.class);

    private Client client;

    public ClientWriteHandler(Client client) {
        this.client = client;
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        log.info("==>write<== | {}", msg.toString());
        super.write(ctx, msg, promise);
    }
}