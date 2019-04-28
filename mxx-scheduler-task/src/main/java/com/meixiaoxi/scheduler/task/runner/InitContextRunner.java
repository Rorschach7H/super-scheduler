package com.meixiaoxi.scheduler.task.runner;

import com.meixiaoxi.scheduler.config.Config;
import com.meixiaoxi.scheduler.config.RedisConfig;
import com.meixiaoxi.scheduler.core.handler.DefaultTaskPersistenceHandler;
import com.meixiaoxi.scheduler.core.handler.TaskPersistenceHandler;
import com.meixiaoxi.scheduler.store.cache.RedissonManager;
import com.meixiaoxi.scheduler.core.processor.PersistenceTaskProcessor;
import com.meixiaoxi.scheduler.core.processor.TaskProcessor;
import com.meixiaoxi.scheduler.task.TaskAppContext;
import org.redisson.api.RedissonClient;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: InitContextRunner
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-04-28 14:33:00
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-04-28    meixiaoxi       v1.0.0           创建
 */
public class InitContextRunner extends TaskRunner<TaskAppContext> {

    private Config config;

    public InitContextRunner(Config config) {
        this.config = config;
    }

    @Override
    public void run() {
        RedisConfig redisConfig = config.getRedisConfig();
        RedissonClient redissonClient = RedissonManager.getRedissonClient(redisConfig);
        context.setRedissonClient(redissonClient);

        TaskPersistenceHandler persistenceHandler = new DefaultTaskPersistenceHandler();

        TaskProcessor taskProcessor = new PersistenceTaskProcessor(redissonClient, persistenceHandler);
        context.setTaskProcessor(taskProcessor);
    }
}
