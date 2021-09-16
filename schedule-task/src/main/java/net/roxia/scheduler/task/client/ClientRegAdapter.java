package net.roxia.scheduler.task.client;

import net.roxia.scheduler.adapter.OperateAdapter;
import net.roxia.scheduler.adapter.annotation.Operate;
import net.roxia.scheduler.common.utils.JsonUtil;
import net.roxia.scheduler.message.body.ClientMsg;
import net.roxia.scheduler.message.protobuf.Message;
import net.roxia.scheduler.message.protobuf.MessageType;
import net.roxia.scheduler.persistence.entity.ClientEntity;
import net.roxia.scheduler.persistence.mapper.ClientMapper;
import net.roxia.scheduler.task.TaskAppContext;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @ClassName ClientRegAdapter
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/7 15:16
 **/
@Operate(operate = MessageType.CONNECT_VALUE)
public class ClientRegAdapter extends OperateAdapter {

    private final ClientMapper clientMapper;

    public ClientRegAdapter() {
        clientMapper = new ClientMapper();
    }

    @Override
    public String handle(Message message) {
        String body = message.getBody();
        if (StringUtils.isBlank(body)) {
            return null;
        }
        ClientMsg clientMsg = JsonUtil.string2Obj(body, ClientMsg.class);
        assert clientMsg != null;
        String clientGroup = clientMsg.getGroup();
        ClientEntity clientEntity = clientMapper.selectByGroup(clientGroup);
        if (clientEntity == null) {
            clientMsg.setActive(false);
            return JsonUtil.obj2String(clientMsg);
        }

        Client client = new Client();
        client.setGroup(clientMsg.getGroup());
        client.setAccessKey(clientMsg.getAccessKey());
        client.setActiveTime(new Date());
        client.setMachineId(clientMsg.getMachineId());
        client.setIp(clientMsg.getIp());
        boolean result = TaskAppContext.getTaskAppContext().getClientContext().putClient(clientGroup, client);
        clientMsg.setActive(result);
        return JsonUtil.obj2String(clientMsg);
    }
}
