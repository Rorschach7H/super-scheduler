package net.roxia.scheduler.core.processor;

import net.roxia.scheduler.adapter.OperateAdapter;
import net.roxia.scheduler.adapter.annotation.Operate;
import net.roxia.scheduler.adapter.enums.OperateEnum;
import net.roxia.scheduler.common.utils.JsonUtil;
import net.roxia.scheduler.message.Message;
import net.roxia.scheduler.message.body.ClientInfo;
import net.roxia.scheduler.persistence.entity.Client;
import net.roxia.scheduler.persistence.mapper.ClientMapper;

/**
 * @ClassName ClientRegAdapter
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/7 15:16
 **/
@Operate(operate = OperateEnum.REG_CLIENT)
public class ClientRegAdapter extends OperateAdapter {

    private final ClientMapper clientMapper;

    public ClientRegAdapter() {
        clientMapper = new ClientMapper();
    }

    @Override
    public String handle(Message message) {
        String body = message.getBody();
        ClientInfo clientInfo = JsonUtil.string2Obj(body, ClientInfo.class);
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
