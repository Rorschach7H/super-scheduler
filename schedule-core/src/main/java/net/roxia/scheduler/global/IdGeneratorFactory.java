package net.roxia.scheduler.global;

import com.google.common.collect.Maps;
import net.roxia.scheduler.redis.RedissonFactory;
import org.redisson.api.RedissonClient;

import java.util.Map;

/**
 * @ClassName IdGeneratorFactory
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/9 11:06
 **/
public class IdGeneratorFactory {

    public static final String ID_PREFIX = "schedule:";

    private static final Map<String, IdGenerator> idGeneratorMap = Maps.newConcurrentMap();

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
