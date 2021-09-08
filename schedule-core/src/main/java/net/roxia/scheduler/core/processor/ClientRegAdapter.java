package net.roxia.scheduler.core.processor;

import com.google.protobuf.InvalidProtocolBufferException;
import net.roxia.scheduler.adapter.OperateAdapter;
import net.roxia.scheduler.adapter.annotation.Operate;
import net.roxia.scheduler.common.utils.JsonUtil;
import net.roxia.scheduler.message.Message;
import net.roxia.scheduler.message.body.ClientInfo;
import net.roxia.scheduler.message.protobuf.ProtoBody;
import net.roxia.scheduler.message.protobuf.ProtoMsg;
import net.roxia.scheduler.persistence.entity.Client;
import net.roxia.scheduler.persistence.mapper.ClientMapper;

/**
 * @ClassName ClientRegAdapter
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/7 15:16
 **/
@Operate(operate = ProtoMsg.MessageType.REG_CLIENT_VALUE)
public class ClientRegAdapter extends OperateAdapter {

    private final ClientMapper clientMapper;

    public ClientRegAdapter() {
        clientMapper = new ClientMapper();
    }

    @Override
    public String handle(ProtoMsg.Message message) {
        ProtoBody.Client clientInfo = null;
        try {
            clientInfo = message.getBody().unpack(ProtoBody.Client.class);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        if (clientInfo == null) {
            return null;
        }
        Client client = new Client();
        client.setAccessKey(clientInfo.getAccessKey());
        client.setClientId(clientInfo.getClientId());
        client.setIp(clientInfo.getIp());
        client.setClientGroup(clientInfo.getGroup());
        if (clientMapper.insertSelective(client)) {
            return "SUCCESS";
        }
        return null;
    }
}
