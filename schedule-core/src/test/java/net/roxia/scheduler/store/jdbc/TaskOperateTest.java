package net.roxia.scheduler.store.jdbc;

import junit.framework.TestCase;
import net.roxia.scheduler.SchedulerConfig;
import net.roxia.scheduler.common.utils.DateUtil;
import net.roxia.scheduler.constant.ConfigKeys;
import net.roxia.scheduler.constant.ConfigSpiKeys;
import net.roxia.scheduler.core.task.domain.RunExecutingTask;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TaskOperateTest extends TestCase {
    {
        SchedulerConfig config = new SchedulerConfig();
        config.put(ConfigKeys.JDBC_URL, "jdbc:mysql://meixiaoxi.com:3306/mxx_schedule_task");
        config.put(ConfigKeys.JDBC_USERNAME, "root");
        config.put(ConfigKeys.JDBC_PASSWORD, "hjw556677");
        config.put(ConfigSpiKeys.DATABASE_SPI, "mysql");
    }

    public void test1InsertTask() {
        RunExecutingTask task = new RunExecutingTask();
        task.setId(1L);
        task.setObjectId(1L);
        task.setCron("");
        task.setPeriod(0);
        task.setTimeUnit((byte) 0);
        task.setInitialDelay(0);
        task.setExecuteTime(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
    }
}
