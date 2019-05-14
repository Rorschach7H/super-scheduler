package com.meixiaoxi.scheduler.store.jdbc;

import com.meixiaoxi.scheduler.SchedulerConfig;
import com.meixiaoxi.scheduler.common.DateUtil;
import com.meixiaoxi.scheduler.constant.ConfigKeys;
import com.meixiaoxi.scheduler.constant.ConfigSpiKeys;
import com.meixiaoxi.scheduler.constant.ConstantsUtil;
import com.meixiaoxi.scheduler.core.task.domain.RunExecutingTask;
import com.meixiaoxi.scheduler.core.task.mysql.MysqlTaskOperate;
import com.meixiaoxi.scheduler.core.task.mysql.TaskOperate;
import junit.framework.TestCase;
import org.junit.Assert;

import java.util.Date;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: TaskOperateTest
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-14 17:07:11
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-14    meixiaoxi       v1.0.0           创建
 */
public class TaskOperateTest extends TestCase {

    private MysqlTaskOperate taskOperate;
    {
        SchedulerConfig config = new SchedulerConfig();
        config.put(ConfigKeys.jdbc_url, "jdbc:mysql://meixiaoxi.com:3306/mxx_schedule_task");
        config.put(ConfigKeys.jdbc_username, "root");
        config.put(ConfigKeys.jdbc_password, "hjw556677");
        config.put(ConfigSpiKeys.DATABASE_SPI, "mysql");
        taskOperate = new MysqlTaskOperate(config);
    }

    public void testInsertTask() {
        RunExecutingTask task = new RunExecutingTask();
        task.setId(1L);
        task.setObjectId(1L);
        task.setGroupKey("test");
        task.setCron("");
        task.setPeriod(0);
        task.setTimeUnit((byte)0);
        task.setInitialDelay(0);
        task.setAccessKey("");
        task.setExecuteTime(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        Assert.assertTrue(taskOperate.insertSelective(task));
    }
}
