package com.meixiaoxi.scheduler.handler;

/**
 * @Title
 * @Description 获取到锁后要处理逻辑的接口
 * @Team 新金融业务研发团队
 * @Author HUANGJUNWEI452
 * @Date 2018/5/23 9:21
 * @Version V1.0
 */
public interface DistributedLockHandler<T> {
    T handleAfterLockAcquire();
}
