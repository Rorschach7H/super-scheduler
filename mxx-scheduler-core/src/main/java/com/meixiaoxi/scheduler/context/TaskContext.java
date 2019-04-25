package com.meixiaoxi.scheduler.context;

import com.meixiaoxi.scheduler.config.CommonConfig;
import com.meixiaoxi.scheduler.processor.TaskProcessor;
import org.redisson.api.RedissonClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: SchedulerContext
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-04-25 14:43:22
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-04-25    meixiaoxi       v1.0.0           创建
 */
public abstract class TaskContext {

    /**
     * 系统配置
     */
    private CommonConfig config;
    /**
     * redisson连接客户端
     */
    private RedissonClient redissonClient;

    /**
     * 任务处理
     */
    private TaskProcessor taskProcessor;

    /**
     * 系统初始化后，实例化的bean管理池
     */
    private Map<String, TaskBeanDefinition> beanDefinitionMap = new HashMap<>();

    public TaskContext(CommonConfig config, RedissonClient redissonClient, TaskProcessor taskProcessor) {
        this.config = config;
        this.redissonClient = redissonClient;
        this.taskProcessor = taskProcessor;
    }

    public CommonConfig getConfig() {
        return config;
    }

    public void setConfig(CommonConfig config) {
        this.config = config;
    }

    public RedissonClient getRedissonClient() {
        return redissonClient;
    }

    public void setRedissonClient(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public TaskProcessor getTaskProcessor() {
        return taskProcessor;
    }

    public void setTaskProcessor(TaskProcessor taskProcessor) {
        this.taskProcessor = taskProcessor;
    }

    public void putBeanDefinition(String key, TaskBeanDefinition beanDefinition) {
        beanDefinitionMap.put(key, beanDefinition);
    }
}
