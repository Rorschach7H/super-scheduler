package net.roxia.scheduler.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.AttributeKey;
import net.roxia.scheduler.message.protobuf.Header;
import net.roxia.scheduler.message.protobuf.Message;
import net.roxia.scheduler.message.protobuf.MessageCode;
import net.roxia.scheduler.message.protobuf.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName AbstractHandler
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/17 15:58
 **/
public abstract class AbstractHandler extends ChannelInboundHandlerAdapter {

    private final Logger log = LoggerFactory.getLogger(AbstractHandler.class);

    private final AttributeKey<String> key = AttributeKey.valueOf("machineId");

    private int heartbeatCount = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (handleMsg(ctx, (Message) msg)) {
            super.channelRead(ctx, msg);
        }
    }

    /**
     * 处理消息
     *
     * @param ctx
     * @param message
     * @return 是否传递至下一个handler继续处理
     */
    protected abstract boolean handleMsg(ChannelHandlerContext ctx, Message message);

    protected void sendPingMsg(ChannelHandlerContext ctx) {
        Header header = Header.newBuilder()
                .setCode(MessageCode.PING)
                .setType(MessageType.CONNECT)
                .build();
        Message msg = Message.newBuilder().setHeader(header).build();
        heartbeatCount++;
        ctx.writeAndFlush(msg);
    }

    protected void sendPongMsg(ChannelHandlerContext ctx) {
        Header header = Header.newBuilder()
                .setCode(MessageCode.PONG)
                .setType(MessageType.CONNECT)
                .build();
        Message msg = Message.newBuilder().setHeader(header).build();
        heartbeatCount++;
        ctx.writeAndFlush(msg);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // IdleStateHandler 所产生的 IdleStateEvent 的处理逻辑.
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            switch (e.state()) {
                case READER_IDLE:
                    handleReaderIdle(ctx);
                    break;
                case WRITER_IDLE:
                    handleWriterIdle(ctx);
                    break;
                case ALL_IDLE:
                    handleAllIdle(ctx);
                    break;
                default:
                    break;
            }
        }
    }

    protected void handleReaderIdle(ChannelHandlerContext ctx) {
        log.info("---READER_IDLE---");
    }

    protected void handleWriterIdle(ChannelHandlerContext ctx) {
        log.info("---WRITER_IDLE---");
    }

    protected void handleAllIdle(ChannelHandlerContext ctx) {
        log.info("---ALL_IDLE---");
    }

    public AttributeKey<String> getMachineIdKey() {
        return key;
    }
}
