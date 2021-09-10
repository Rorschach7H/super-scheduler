package net.roxia.scheduler.task.client;

import com.google.common.collect.Maps;
import net.roxia.scheduler.cache.CacheInterface;
import net.roxia.scheduler.spi.ServiceLoader;

import java.util.Map;

/**
 * @ClassName ClientContext
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/9 11:42
 **/
public class ClientContext {

    private static final String SPLIT = "_";

    private static final Map<String, Client> onlineClientMap = Maps.newConcurrentMap();

    private final CacheInterface cacheInterface;

    public ClientContext() {
        cacheInterface = ServiceLoader.load(CacheInterface.class);
    }

    public Client getClient(String group, String machineId) {
        String key = group + SPLIT + machineId;
        Client client = onlineClientMap.get(key);
        if (client != null) {
            return client;
        }
        client = cacheInterface.getCache(key);
        if (client != null) {
            onlineClientMap.put(key, client);
            return client;
        }
        return null;
    }

    public void putClient(String group, Client client) {
        onlineClientMap.put(group + SPLIT + client.getMachineId(), client);
    }
}
