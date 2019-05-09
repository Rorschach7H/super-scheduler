package com.meixiaoxi.scheduler.core.task.mysql;

import com.meixiaoxi.scheduler.SchedulerConfig;
import com.meixiaoxi.scheduler.core.task.TaskOperate;
import com.meixiaoxi.scheduler.core.task.domain.RunExecutingTask;
import com.meixiaoxi.scheduler.core.task.domain.TaskQuery;
import com.meixiaoxi.scheduler.store.jdbc.JdbcAbstractAccess;

import javax.sql.DataSource;
import java.util.List;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: DefaultTaskPersistenceHandler
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-03-05 09:34:41
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-03-05    meixiaoxi       v1.0.0           创建
 */
public class AbstractMysqlTaskOperate extends JdbcAbstractAccess implements TaskOperate {

    public AbstractMysqlTaskOperate(SchedulerConfig config) {
        super(config);
    }

    @Override
    public boolean insert(RunExecutingTask task) {
        return false;
    }

    @Override
    public boolean insertBatch(List<RunExecutingTask> task) {
        return false;
    }

    @Override
    public boolean update(RunExecutingTask task) {
        return false;
    }

    @Override
    public boolean updateBatch(List<RunExecutingTask> tasks) {
        return false;
    }

    @Override
    public int delete(Long taskId) {
        return 0;
    }

    @Override
    public RunExecutingTask select(Long id) {
        return null;
    }

    @Override
    public List<RunExecutingTask> select(TaskQuery query) {
        return null;
    }
}
