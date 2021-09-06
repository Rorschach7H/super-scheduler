package net.roxia.scheduler.core.task.mysql.support;

import net.roxia.scheduler.core.task.domain.RunExecutingTask;
import net.roxia.scheduler.store.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: ResultSetHandlerHolder
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-14 16:25:37
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-14    meixiaoxi       v1.0.0           创建
 */
public class ResultSetHandlerHolder {
    public static final ResultSetHandler<RunExecutingTask> RUN_EXECUTING_TASK_RSH =
            ResultSetHandlerHolder::getRunExecutingTask;

    private static RunExecutingTask getRunExecutingTask(ResultSet rs) throws SQLException {
        if (!rs.next()) {
            return null;
        }
        RunExecutingTask task = new RunExecutingTask();
        task.setId(rs.getLong(1));
        task.setObjectId(rs.getLong(2));
        task.setTaskName(rs.getString(3));
        task.setCron(rs.getString(5));
        task.setPeriod(rs.getInt(6));
        task.setTimeUnit(rs.getByte(7));
        task.setInitialDelay(rs.getInt(8));
        task.setExecuteTime(rs.getString(10));
        task.setExecuteQueue(rs.getLong(11));
        task.setFailures(rs.getInt(12));
        task.setMaxFailures(rs.getInt(13));
        task.setExecuteState(rs.getByte(14));
        task.setCreateTime(rs.getString(15));
        return task;
    }
}
