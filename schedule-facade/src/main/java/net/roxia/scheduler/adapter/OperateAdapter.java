package net.roxia.scheduler.adapter;

import com.google.common.collect.Maps;
import net.roxia.scheduler.adapter.annotation.Operate;
import net.roxia.scheduler.message.enums.BizMessageType;
import net.roxia.scheduler.message.protobuf.Message;

import java.util.Map;

/**
 * @ClassName OperateAdapter
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/3 15:27
 **/
public abstract class OperateAdapter {

    private static final Map<BizMessageType, OperateAdapter> adapterMap = Maps.newConcurrentMap();

    public abstract String handle(Message message);

    public static void initAdapterMap(OperateAdapter... adapters) {
        for (OperateAdapter adapter : adapters) {
            Operate operate = adapter.getClass().getAnnotation(Operate.class);
            if (operate == null) {
                return;
            }
            OperateAdapter.adapterMap.put(operate.operate(), adapter);
        }
    }

    public static OperateAdapter getOperateAdapter(BizMessageType operate) {
        return adapterMap.get(operate);
    }
}
