package com.meixiaoxi.scheduler.consumer;

import com.meixiaoxi.scheduler.config.CommonConfig;
import com.meixiaoxi.scheduler.context.TaskContext;
import com.meixiaoxi.scheduler.handler.TaskExecuteHandler;
import com.meixiaoxi.scheduler.processor.TaskProcessor;
import org.redisson.api.RedissonClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class ConsumerTaskContext extends TaskContext {

    public Map<String, TaskExecuteHandler> taskExecuteHandlerMap = new HashMap<>();
    public List<String> executeHandlerKeyList = new ArrayList<>();


    public ConsumerTaskContext(CommonConfig config, RedissonClient redissonClient, TaskProcessor taskProcessor) {
        super(config, redissonClient, taskProcessor);
    }
}
