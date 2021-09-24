package net.roxia.scheduler.task.server.handler;

import io.netty.channel.ChannelHandlerContext;
import net.roxia.scheduler.handler.AbstractHandler;
import net.roxia.scheduler.message.enums.SysMessageType;
import net.roxia.scheduler.message.protobuf.Header;
import net.roxia.scheduler.message.protobuf.Message;
import net.roxia.scheduler.persistence.mapper.ClientGroupMapper;
import net.roxia.scheduler.task.TaskAppContext;
import net.roxia.scheduler.task.client.Client;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TokenAuthHandler extends AbstractHandler {

    private final static Logger log = LoggerFactory.getLogger(TokenAuthHandler.class);

    private ClientGroupMapper clientGroupMapper;

    public TokenAuthHandler() {
        clientGroupMapper = new ClientGroupMapper();
    }

    @Override
    public boolean handleMsg(ChannelHandlerContext ctx, Message message) {
        Header header = message.getHeader();
        Pair<Boolean, String> checkResult = checkHeader(header);
        if (!checkResult.getLeft()) {
            log.warn("client [{}] auth failed! | {}", header.getMachineId(), checkResult.getRight());
            header = Header.newBuilder(header)
                    .setSysType(SysMessageType.AUTH_FAIL.name())
                    .build();
            message = Message.newBuilder().setHeader(header).setBody(checkResult.getRight()).build();
            ctx.writeAndFlush(message);
            return false;
        }
        return true;
    }

    /**
     * 校验消息有效性
     *
     * @param header
     * @return
     */
    private Pair<Boolean, String> checkHeader(Header header) {
        boolean flag = !(StringUtils.isAnyEmpty(
                header.getAccessKey(),
                header.getVersion(),
                header.getGroup(),
                header.getMachineId(),
                header.getRequestId()) && (header.getSysType() != null || header.getBizType() != null));
        if (!flag) {
            return Pair.of(false, "Client header check failed! ");
        }
        Client client = TaskAppContext.getTaskAppContext()
                .getClientContext().getClient(header.getGroup(), header.getMachineId());
        if (client == null) {
            return Pair.of(false, "Client header has not registered! ");
        }
        if (!StringUtils.equals(client.getAccessKey(), header.getAccessKey())) {
            return Pair.of(false, "Client access key error! ");
        }
        return Pair.of(true, StringUtils.EMPTY);
    }
}