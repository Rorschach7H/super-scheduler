package com.meixiaoxi.scheduler.task;

import com.meixiaoxi.scheduler.AppContext;
import com.meixiaoxi.scheduler.SchedulerConfig;
import com.meixiaoxi.scheduler.core.processor.TaskProcessor;
import org.redisson.api.RedissonClient;

import java.util.*;

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
public class TaskAppContext extends AppContext {

    public Map<String, Runnable> runnerLinkedMap = new HashMap<>();
    public List<String> executeHandlerAlisList = new ArrayList<>();

    public TaskAppContext(SchedulerConfig config) {
        super(config);
    }

    public TaskAppContext(SchedulerConfig config, RedissonClient redissonClient, TaskProcessor taskProcessor) {
        super(config, redissonClient, taskProcessor);
    }
}
