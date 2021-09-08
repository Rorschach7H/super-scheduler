package net.roxia.scheduler.adapter;

import com.google.common.collect.Maps;
import net.roxia.scheduler.adapter.annotation.Operate;
import net.roxia.scheduler.message.protobuf.ProtoMsg;

import java.util.Map;

/**
 * @ClassName OperateAdapter
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/3 15:27
 **/
public abstract class OperateAdapter {
    private static final Map<ProtoMsg.MessageType, OperateAdapter> adapterMap = Maps.newConcurrentMap();

    public abstract String handle(ProtoMsg.Message message);

    public static void initAdapterMap(OperateAdapter... adapters) {
        for (OperateAdapter adapter : adapters) {
            Operate operate = adapter.getClass().getAnnotation(Operate.class);
            if (operate == null) {
                return;
            }
            ProtoMsg.MessageType type = ProtoMsg.MessageType.forNumber(operate.operate());
            OperateAdapter.adapterMap.put(type, adapter);
        }
    }

    public static OperateAdapter getOperateAdapter(ProtoMsg.MessageType operate){
        return adapterMap.get(operate);
    }
}
