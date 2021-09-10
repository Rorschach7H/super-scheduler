package net.roxia.scheduler.adapter;

import com.google.common.collect.Maps;
import net.roxia.scheduler.adapter.annotation.Operate;
import net.roxia.scheduler.message.protobuf.Message;
import net.roxia.scheduler.message.protobuf.MessageType;

import java.util.Map;

/**
 * @ClassName OperateAdapter
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/3 15:27
 **/
public abstract class OperateAdapter {

    private static final Map<MessageType, OperateAdapter> adapterMap = Maps.newConcurrentMap();

    public abstract String handle(Message message);

    public static void initAdapterMap(OperateAdapter... adapters) {
        for (OperateAdapter adapter : adapters) {
            Operate operate = adapter.getClass().getAnnotation(Operate.class);
            if (operate == null) {
                return;
            }
            MessageType type = MessageType.forNumber(operate.operate());
            OperateAdapter.adapterMap.put(type, adapter);
        }
    }

    public static OperateAdapter getOperateAdapter(MessageType operate) {
        return adapterMap.get(operate);
    }
}
