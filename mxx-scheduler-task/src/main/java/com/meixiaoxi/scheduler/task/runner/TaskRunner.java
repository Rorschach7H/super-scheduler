package com.meixiaoxi.scheduler.task.runner;

import com.meixiaoxi.scheduler.core.result.Result;
import com.meixiaoxi.scheduler.task.TaskAppContext;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: Runner
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-04-25 15:00:31
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-04-25    meixiaoxi       v1.0.0           创建
 */
public interface TaskRunner {
    Result run(TaskAppContext context);
}
