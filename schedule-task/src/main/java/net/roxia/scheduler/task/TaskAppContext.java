package net.roxia.scheduler.task;

import com.google.common.collect.Maps;
import net.roxia.scheduler.AppContext;
import net.roxia.scheduler.SchedulerConfig;
import net.roxia.scheduler.factory.DefaultServiceFactory;
import net.roxia.scheduler.global.DefaultIdGenerator;
import net.roxia.scheduler.global.IdGenerator;
import net.roxia.scheduler.redis.RedissonFactory;
import net.roxia.scheduler.task.client.ClientContext;
import org.redisson.api.RedissonClient;

import java.util.Map;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: ConsumerTaskContext
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-04-25 16:35:33
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-04-25    meixiaoxi       v1.0.0           创建
 */
public class TaskAppContext extends DefaultServiceFactory implements AppContext {
    /**
     * 系统配置
     */
    private SchedulerConfig config;

    private ClientContext clientContext;

    public static final String ID_PREFIX = "schedule:";

    private static final Map<String, IdGenerator> idGeneratorMap = Maps.newConcurrentMap();

    private static TaskAppContext taskAppContext;

    private TaskAppContext(SchedulerConfig config) {
        this.clientContext = new ClientContext();
        this.config = config;
    }

    public static TaskAppContext init(SchedulerConfig config) {
        taskAppContext = new TaskAppContext(config);
        return taskAppContext;
    }

    @Override
    public SchedulerConfig getConfig() {
        return config;
    }

    public static TaskAppContext getTaskAppContext() {
        return taskAppContext;
    }

    public ClientContext getClientContext() {
        return clientContext;
    }

    public void setClientContext(ClientContext clientContext) {
        this.clientContext = clientContext;
    }

    public static IdGenerator getIdGenerator(String key) {
        IdGenerator idGenerator = idGeneratorMap.get(key);
        if (idGenerator != null) {
            return idGenerator;
        }

        RedissonClient redissonClient = RedissonFactory.getRedissonClient();
        long workerId = redissonClient.getAtomicLong(ID_PREFIX + key).incrementAndGet();
        idGenerator = new DefaultIdGenerator(workerId);
        idGeneratorMap.put(key, idGenerator);
        return idGenerator;
    }
}
