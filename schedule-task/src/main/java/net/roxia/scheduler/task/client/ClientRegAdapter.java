package net.roxia.scheduler.task.client;

import net.roxia.scheduler.adapter.OperateAdapter;
import net.roxia.scheduler.adapter.annotation.Operate;
import net.roxia.scheduler.common.utils.JsonUtil;
import net.roxia.scheduler.message.body.ClientMsg;
import net.roxia.scheduler.message.protobuf.Message;
import net.roxia.scheduler.message.protobuf.MessageType;
import net.roxia.scheduler.persistence.mapper.ClientMapper;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName ClientRegAdapter
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/7 15:16
 **/
@Operate(operate = MessageType.REG_CLIENT_VALUE)
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


        return null;
    }
}
