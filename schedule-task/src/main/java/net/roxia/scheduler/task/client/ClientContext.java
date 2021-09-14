package net.roxia.scheduler.task.client;

import com.google.common.collect.Maps;
import net.roxia.scheduler.cache.CacheInterface;
import net.roxia.scheduler.common.utils.JsonUtil;
import net.roxia.scheduler.spi.ServiceLoader;
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

    private static final Map<String, Map<String, Client>> onlineClientMap = Maps.newConcurrentMap();

    private final CacheInterface cacheInterface;

    public ClientContext() {
        cacheInterface = ServiceLoader.load(CacheInterface.class);
    }

    public Client getClient(String group, String machineId) {
        Map<String, Client> clientMap = onlineClientMap.get(group);
        if (MapUtils.isEmpty(clientMap)) {
            clientMap = Maps.newConcurrentMap();
            onlineClientMap.put(group, clientMap);
        }
        Client client = clientMap.get(machineId);
        if (client != null) {
            return client;
        }
        client = cacheInterface.getCache(machineId);
        if (client != null) {
            clientMap.put(machineId, client);
        }
        return client;
    }

    public boolean removeClient(String group, String machineId) {
        Map<String, Client> clientMap = onlineClientMap.get(group);
        if (MapUtils.isEmpty(clientMap)) {
            return true;
        }
        clientMap.remove(machineId);
        cacheInterface.remove(machineId);
        cacheInterface.removeList(group, machineId);
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
            cacheInterface.addList(group, client.getMachineId());
            cacheInterface.setCache(client.getMachineId(), client);
        } catch (Exception e) {
            log.error("active client error! {}", JsonUtil.obj2String(client), e);
            return false;
        }
        return true;
    }
}
