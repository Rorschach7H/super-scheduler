package net.roxia.scheduler.task.server.handler;

import io.netty.channel.ChannelHandlerContext;
import net.roxia.scheduler.common.utils.DateUtil;
import net.roxia.scheduler.common.utils.JsonUtil;
import net.roxia.scheduler.handler.AbstractHandler;
import net.roxia.scheduler.holder.IdGenerateHolder;
import net.roxia.scheduler.message.body.ClientMsg;
import net.roxia.scheduler.message.enums.SysMessageType;
import net.roxia.scheduler.message.protobuf.Header;
import net.roxia.scheduler.message.protobuf.Message;
import net.roxia.scheduler.persistence.entity.ClientActive;
import net.roxia.scheduler.persistence.mapper.ClientActiveMapper;
import net.roxia.scheduler.task.TaskAppContext;
import net.roxia.scheduler.task.client.Client;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;

/**
 * @ClassName ConnectHandler
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/16 18:01
 **/
public class ConnectHandler extends AbstractHandler {

    private final static Logger log = LoggerFactory.getLogger(ConnectHandler.class);

    private final ClientActiveMapper clientActiveMapper = new ClientActiveMapper();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String machineId = TaskAppContext.getIdGenerator("machine").getIdString();
        TaskAppContext.getTaskAppContext().getClientContext().putChannelHandlerContext(machineId, ctx);
        ctx.channel().attr(getMachineIdKey()).set(machineId);
        Header header = Header.newBuilder()
                .setRequestId(IdGenerateHolder.generateRequestId(SysMessageType.HANDSHAKE, machineId))
                .setSysType(SysMessageType.HANDSHAKE.name())
                .setMachineId(machineId)
                .build();

        ctx.writeAndFlush(Message.newBuilder().setHeader(header).build());

        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        String machineId = String.valueOf(ctx.channel().attr(getMachineIdKey()).get());
        log.info("client [{}] offline!", machineId);
        clientOffline(machineId);
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        String machineId = String.valueOf(ctx.channel().attr(getMachineIdKey()).get());
        if (cause instanceof IOException) {
            log.warn("client [{}] connect closed!", machineId);
            return;
        }
        log.error("client [{}] channel happened error", machineId, cause);
    }

    @Override
    protected boolean handleMsg(ChannelHandlerContext ctx, Message msg) {
        SysMessageType sysMessageType = SysMessageType.value(msg.getHeader().getSysType());
        if (sysMessageType == SysMessageType.CONNECT) {
            clientConnect(msg);
            return false;
        }
        if (sysMessageType == SysMessageType.PING) {
            sendPongMsg(ctx);
            return false;
        }
        return true;
    }

    @Override
    protected void handleReaderIdle(ChannelHandlerContext ctx) {
        super.handleReaderIdle(ctx);
        log.warn("client [{}] reader timeout, close it", ctx.channel().attr(getMachineIdKey()).get());
        ctx.close();
    }

    private void clientConnect(Message message) {
        String body = message.getBody();
        Header header = message.getHeader();
        if (StringUtils.isBlank(body)) {
            return;
        }
        ClientMsg clientMsg = JsonUtil.string2Obj(body, ClientMsg.class);
        assert clientMsg != null;
        String group = clientMsg.getGroup();
        Date activeTime = new Date();
        ClientActive clientActive = new ClientActive();
        clientActive.setGroup(header.getGroup());
        clientActive.setActiveTime(activeTime);
        clientActive.setMachineId(header.getMachineId());
        clientActive.setIp(clientMsg.getIp());
        clientActive.setActive(1);
        if (clientActiveMapper.selectCountByMachineId(clientMsg.getMachineId()) == 0) {
            clientActiveMapper.insertSelective(clientActive);
        } else {
            clientActiveMapper.update(clientActive);
        }

        Client client = new Client();
        client.setGroup(header.getGroup());
        client.setIp(clientMsg.getIp());
        client.setMachineId(header.getMachineId());
        client.setAccessKey(header.getAccessKey());
        client.setActiveTime(DateUtil.dateToString(activeTime));
        TaskAppContext.getTaskAppContext().getClientContext().putClient(group, client);
    }

    private void clientOffline(String machineId) {
        try {
            ClientActive clientActive = clientActiveMapper.selectByMachineId(machineId);
            if (clientActive == null) {
                return;
            }
            clientActive.setActive(0);
            clientActive.setOfflineTime(new Date());
            clientActiveMapper.update(clientActive);
            TaskAppContext.getTaskAppContext().getClientContext().removeClient(clientActive.getGroup(), machineId);
            TaskAppContext.getTaskAppContext().getClientContext().removeChannelHandlerContext(machineId);
        } catch (Exception e) {
            log.error("client [{}] offline error!", machineId, e);
        }
    }
}
