package com.meixiaoxi.scheduler.lock;

import com.meixiaoxi.scheduler.manager.RedissonManager;
import com.meixiaoxi.scheduler.handler.DistributedLockHandler;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

public class DistributedLockWrapper implements DistributedLock {

    private RLock lock;

    /**
     * default lock name
     */
    private static final String DEFAULT_LOCK_NAME = "distributed_lock_key";

    /**
     * 等待锁超时时间
     */
    private static final int WAIT_EXPIRE_TIME = 10 * 1000;

    public DistributedLockWrapper() {
        this(DEFAULT_LOCK_NAME);
    }

    public DistributedLockWrapper(String key) {
        RedissonClient redissonClient = RedissonManager.getRedissonClient();
        this.lock = redissonClient.getLock(key);
    }

    @Override
    public <T> T lock(DistributedLockHandler<T> handler) throws Exception {
        return lock(handler, WAIT_EXPIRE_TIME);
    }

    @Override
    public <T> T lock(DistributedLockHandler<T> handler, long waitTime) throws Exception {
        if (lock.tryLock(waitTime, TimeUnit.MICROSECONDS)) {
            try {
                return handler.handleAfterLockAcquire();
            } finally {
                tryUnlock();
            }
        }
        throw new RuntimeException("未获取到锁！");
    }

    private void tryUnlock() {
        lock.unlock();
    }
}