package net.roxia.scheduler.cache;

import net.roxia.scheduler.constant.ConfigSpiKeys;
import net.roxia.scheduler.spi.SPI;
import org.redisson.api.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CacheInterface
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/9 11:53
 **/
@SPI(defaultName = "cache", dynamicKey = ConfigSpiKeys.CACHE_SPI)
public interface CacheInterface {

    public boolean getDistributedLock(String key, int waitTime, int lockTime);

    public void releaseDistributedLock(String key);

    public long increaseAtomicLong(String key);

    public long increaseAtomicLong(String key, long expireTime, TimeUnit timeUnit);

    public <T> RBlockingQueue<T> getBlockingQueue(String key);

    public <T> RDelayedQueue<T> getDelayQueue(RBlockingQueue<T> blockingQueue);

    public <T> void setDelayQueueData(String key, T value, long delay, TimeUnit timeUnit);

    public <T> T getDelayQueueData(String key);

    public <T> T getCache(String key);

    public <T> void setCache(String key, T t, long time, TimeUnit timeUnit);

    public <T> void setCache(String key, T t);

    public void remove(String key);

    public <T> List<T> getList(String key);

    public <T> void addList(String key, T t);

    public <T> void removeList(String key, T t);
}
