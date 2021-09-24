package net.roxia.scheduler.task.client;

import com.google.common.collect.Maps;
import io.netty.channel.ChannelHandlerContext;
import net.roxia.scheduler.common.utils.JsonUtil;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @ClassName ClientContext
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/9 11:42
 **/
public class ClientContext {

    private final Logger log = LoggerFactory.getLogger(ClientContext.class);

    private static final Map<String, ChannelHandlerContext> channelHandlerContextMap = Maps.newConcurrentMap();

    private static final Map<String, Map<String, Client>> onlineClientMap = Maps.newConcurrentMap();

    public Client getClient(String group, String machineId) {
        Map<String, Client> clientMap = onlineClientMap.get(group);
        if (MapUtils.isEmpty(clientMap)) {
            clientMap = Maps.newConcurrentMap();
            onlineClientMap.put(group, clientMap);
        }
        return clientMap.get(machineId);
    }

    public boolean removeClient(String group, String machineId) {
        Map<String, Client> clientMap = onlineClientMap.get(group);
        if (MapUtils.isEmpty(clientMap)) {
            return true;
        }
        clientMap.remove(machineId);
        return true;
    }

    public boolean putClient(String group, Client client) {
        try {
            Map<String, Client> clientMap = onlineClientMap.get(group);
            if (MapUtils.isEmpty(clientMap)) {
                clientMap = Maps.newConcurrentMap();
                onlineClientMap.put(group, clientMap);
            }
            clientMap.put(client.getMachineId(), client);
        } catch (Exception e) {
            log.error("active client error! {}", JsonUtil.obj2String(client), e);
            return false;
        }
        return true;
    }

    public void putChannelHandlerContext(String machineId, ChannelHandlerContext channelHandlerContext) {
        channelHandlerContextMap.put(machineId, channelHandlerContext);
    }

    public void removeChannelHandlerContext(String machineId) {
        channelHandlerContextMap.remove(machineId);
    }

    public ChannelHandlerContext getChannelHandlerContext(String machineId) {
        return channelHandlerContextMap.get(machineId);
    }
}
