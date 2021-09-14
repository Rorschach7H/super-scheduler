package net.roxia.scheduler.cache;

import lombok.extern.slf4j.Slf4j;
import net.roxia.scheduler.redis.RedissonFactory;
import org.redisson.api.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedissonCache
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/3/26 11:01
 **/
@Slf4j
public class RedissonCache implements CacheInterface{

    private final RedissonClient redissonClient;

    public RedissonCache() {
        redissonClient = RedissonFactory.getRedissonClient();
    }

    public boolean getDistributedLock(String key, int waitTime, int lockTime) {
        try {
            RLock lock = redissonClient.getLock(key);
            return lock.tryLock(waitTime, lockTime, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            log.error("get distributed lock error! | key: {} | waitTime: {} | lockTime: {}", key, waitTime, lockTime, e);
            return false;
        }
    }

    public void releaseDistributedLock(String key) {
        try {
            RLock lock = redissonClient.getLock(key);
            if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        } catch (Exception e) {
            log.error("release distributed lock error!", e);
        }
    }

    public long increaseAtomicLong(String key) {
        long count = 0;
        try {
            RAtomicLong atomicLong = redissonClient.getAtomicLong(key);
            count = atomicLong.incrementAndGet();
        } catch (Exception e) {
            log.error("redis increase atomic long error! ", e);
        }
        return count;
    }

    public long increaseAtomicLong(String key, long expireTime, TimeUnit timeUnit) {
        long count = 0;
        try {
            RAtomicLong atomicLong = redissonClient.getAtomicLong(key);
            count = atomicLong.incrementAndGet();
            atomicLong.expire(expireTime, timeUnit);
        } catch (Exception e) {
            log.error("redis increase atomic long error! ", e);
        }
        return count;
    }

    public <T> RBlockingQueue<T> getBlockingQueue(String key) {
        try {
            return redissonClient.getBlockingQueue(key);
        } catch (Exception e) {
            log.error("redis get blocking queue error! ", e);
        }
        return null;
    }

    public <T> RDelayedQueue<T> getDelayQueue(RBlockingQueue<T> blockingQueue) {
        try {
            return redissonClient.getDelayedQueue(blockingQueue);
        } catch (Exception e) {
            log.error("redis get delaying queue error! ", e);
        }
        return null;
    }

    public <T> void setDelayQueueData(String key, T value, long delay, TimeUnit timeUnit) {
        try {
            RBlockingQueue<T> blockingQueue = redissonClient.getBlockingQueue(key);
            RDelayedQueue<T> delayedQueue = redissonClient.getDelayedQueue(blockingQueue);
            delayedQueue.offer(value, delay, timeUnit);
        } catch (Exception e) {
            log.error("redis set Relay Queue Data queue error! ", e);
        }
    }

    public <T> T getDelayQueueData(String key) {
        RBlockingQueue<T> blockingQueue = redissonClient.getBlockingQueue(key);
        try {
            return blockingQueue.take();
        } catch (Exception e) {
            log.error("redis get relay queue data error! | {}", e.getMessage());
        }
        return null;
    }

    public <T> T getCache(String key) {
        try {
            RBucket<T> bucket = redissonClient.getBucket(key);
            return bucket.get();
        } catch (Exception e) {
            log.error("get cache data error! | key : {}", key, e);
            return null;
        }
    }

    public <T> void setCache(String key, T t, long time, TimeUnit timeUnit) {
        try {
            RBucket<T> bucket = redissonClient.getBucket(key);
            bucket.set(t, time, timeUnit);
        } catch (Exception e) {
            log.error("set cache data error! | key : {}", key, e);
        }
    }

    public <T> void setCache(String key, T t) {
        try {
            RBucket<T> bucket = redissonClient.getBucket(key);
            bucket.set(t);
        } catch (Exception e) {
            log.error("set cache data error! | key : {}", key, e);
        }
    }

    public void remove(String key) {
        try {
            redissonClient.getBucket(key).delete();
        } catch (Exception e) {
            log.error("set cache data error! | key : {}", key, e);
        }
    }

    public <T> List<T> getList(String key) {
        try {
            RList<T> list = redissonClient.getList(key);
            return list.readAll();
        } catch (Exception e) {
            log.error("get list cache data error! | key : {}", key, e);
            return null;
        }
    }

    public <T> void addList(String key, T t) {
        try {
            RList<T> list = redissonClient.getList(key);
            list.add(t);
        } catch (Exception e) {
            log.error("add list cache data error! | key : {}", key, e);
        }
    }

    @Override
    public <T> void removeList(String key, T t) {
        try {
            RList<T> list = redissonClient.getList(key);
            list.remove(t);
        } catch (Exception e) {
            log.error("remove list cache data error! | key : {}", key, e);
        }
    }
}
