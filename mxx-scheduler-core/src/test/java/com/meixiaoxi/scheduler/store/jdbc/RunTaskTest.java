package com.meixiaoxi.scheduler.store.jdbc;

import com.meixiaoxi.scheduler.core.task.domain.RunExecutingTask;
import junit.framework.TestCase;

import java.util.Arrays;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: RunTaskTest
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-14 14:14:20
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-14    meixiaoxi       v1.0.0           创建
 */
public class RunTaskTest extends TestCase {
    public void testListColumns(){
        RunExecutingTask task = new RunExecutingTask();
        task.setId(1L);
        task.setTaskName("task");
        task.setFailures(3);

        System.out.println(Arrays.toString(task.columns()));
        System.out.println(Arrays.toString(task.values()));
        System.out.println(task.keyValueMap(false));
    }
}
