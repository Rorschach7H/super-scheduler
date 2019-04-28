package com.meixiaoxi.scheduler.lock;

/**
 * @Title
 * @Description 锁包装
 * @Team 新金融业务研发团队
 * @Author HUANGJUNWEI452
 * @Date 2018/5/23 9:21
 * @Version V1.0
 */
public interface DistributedLock {

    <T> T lock(DistributedLockHandler<T> handler) throws Exception;

    <T> T lock(DistributedLockHandler<T> handler, long waitTime) throws Exception;
}