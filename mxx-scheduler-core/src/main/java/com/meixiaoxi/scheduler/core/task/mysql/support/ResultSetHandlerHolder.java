package com.meixiaoxi.scheduler.core.task.mysql.support;

import com.meixiaoxi.scheduler.core.task.domain.RunExecutingTask;
import com.meixiaoxi.scheduler.store.dbutils.ResultSetHandler;

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
        task.setGroupKey(rs.getString(3));
        task.setCron(rs.getString(4));
        task.setPeriod(rs.getInt(5));
        task.setTimeUnit(rs.getByte(6));
        task.setInitialDelay(rs.getInt(7));
        task.setAccessKey(rs.getString(8));
        task.setExecuteTime(rs.getString(9));
        task.setExecuteQueue(rs.getLong(10));
        task.setFailures(rs.getInt(11));
        task.setMaxFailures(rs.getInt(12));
        task.setExecuteState(rs.getByte(13));
        task.setCreateTime(rs.getString(14));

        return task;
    }
}