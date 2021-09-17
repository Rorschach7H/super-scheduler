package net.roxia.scheduler.task.server.handler;

import io.netty.channel.ChannelHandlerContext;
import net.roxia.scheduler.handler.AbstractHandler;
import net.roxia.scheduler.message.protobuf.Header;
import net.roxia.scheduler.message.protobuf.Message;
import net.roxia.scheduler.message.protobuf.MessageCode;
import net.roxia.scheduler.message.protobuf.MessageType;
import net.roxia.scheduler.persistence.mapper.ClientMapper;
import net.roxia.scheduler.task.TaskAppContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TokenAuthHandler extends AbstractHandler {

    private final static Logger log = LoggerFactory.getLogger(TokenAuthHandler.class);

    private ClientMapper clientMapper;

    public TokenAuthHandler() {
        clientMapper = new ClientMapper();
    }

    @Override
    public boolean handleMsg(ChannelHandlerContext ctx, Message message) {
        Header header = message.getHeader();
        Pair<Boolean, String> checkResult = checkHeader(header);
        if (!checkResult.getLeft()) {
            TaskAppContext.getTaskAppContext().getClientContext().removeChannelHandlerContext(header.getMachineId());
            header = Header.newBuilder(header)
                    .setType(MessageType.CONNECT)
                    .setCode(MessageCode.AUTH_FAIL)
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
                header.getRequestId()) && header.getType() != null);
        if (!flag) {
            return Pair.of(false, "Client header check failed! ");
        }
        if (clientMapper.selectCountByGroup(header.getGroup()) == 0) {
            return Pair.of(false, "Client header has not registered! ");
        }
        return Pair.of(true, StringUtils.EMPTY);
    }
}