package com.meixiaoxi.scheduler.core.task.mysql;

import com.meixiaoxi.scheduler.SchedulerConfig;
import com.meixiaoxi.scheduler.core.task.domain.RunExecutingTask;
import com.meixiaoxi.scheduler.core.task.domain.TableName;
import com.meixiaoxi.scheduler.core.task.domain.TaskQuery;
import com.meixiaoxi.scheduler.core.task.mysql.support.ResultSetHandlerHolder;
import com.meixiaoxi.scheduler.store.JdbcAbstractAccess;
import com.meixiaoxi.scheduler.store.builder.DeleteSql;
import com.meixiaoxi.scheduler.store.builder.InsertSql;
import com.meixiaoxi.scheduler.store.builder.SelectSql;
import com.meixiaoxi.scheduler.store.builder.UpdateSql;
import com.meixiaoxi.scheduler.store.dbutils.ResultSetHandler;
import org.apache.commons.collections4.CollectionUtils;
import org.h2.command.dml.Select;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    public boolean insertSelective(RunExecutingTask task) {
        Map<String, Object> keyValueMap = task.keyValueMap(true);
        List<String> columns = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        keyValueMap.forEach((k, v) -> {
            if (v != null) {
                columns.add(k);
                values.add(v);
            }
        });
        String[] columnArray = new String[columns.size()];
        columns.toArray(columnArray);
        InsertSql insertSql = new InsertSql(getSqlTemplate())
                .insert(task.tableName())
                .columns(columnArray)
                .values(values.toArray());

        return insertSql.doInsert() == 1;
    }

    @Override
    public boolean insert(RunExecutingTask task) {

        InsertSql insertSql = new InsertSql(getSqlTemplate())
                .insert(task.tableName())
                .columns(task.columns())
                .values(task.values());

        return insertSql.doInsert() == 1;
    }

    @Override
    public boolean insertBatch(List<RunExecutingTask> tasks) {

        if (CollectionUtils.isEmpty(tasks)) {
            return false;
        }

        InsertSql insertSql = new InsertSql(getSqlTemplate())
                .insert(tasks.get(0).tableName())
                .columns(tasks.get(0).columns());
        tasks.forEach(task -> insertSql.values(task.values()));

        return insertSql.doInsert() == tasks.size();
    }

    @Override
    public boolean update(RunExecutingTask task) {
        UpdateSql updateSql = new UpdateSql(getSqlTemplate())
                .update().table(task.tableName());
        Map<String, Object> keyValueMap = task.keyValueMap(true);
        keyValueMap.forEach(updateSql::set);
        updateSql.where(task.primaryKey() + "=?", task.primaryValue());
        return updateSql.doUpdate() == 1;
    }

    @Override
    public boolean updateBatch(List<RunExecutingTask> tasks) {
        if (CollectionUtils.isEmpty(tasks)) {
            return false;
        }
        long count = tasks.stream().map(this::update).filter(e -> e).count();
        return count == tasks.size();
    }

    @Override
    public boolean delete(Long taskId) {
        return new DeleteSql(getSqlTemplate())
                .delete()
                .from()
                .table(RunExecutingTask.tableName(RunExecutingTask.class))
                .where(RunExecutingTask.primaryKey(RunExecutingTask.class) + "=?", taskId)
                .doDelete() == 1;
    }

    @Override
    public RunExecutingTask select(Long id) {
        return new SelectSql(getSqlTemplate())
                .select()
                .columns(RunExecutingTask.columns(RunExecutingTask.class))
                .from()
                .table(RunExecutingTask.tableName(RunExecutingTask.class))
                .where(RunExecutingTask.primaryKey(RunExecutingTask.class) + "=?", id)
                .single(ResultSetHandlerHolder.RUN_EXECUTING_TASK_RSH);

    }

    @Override
    public List<RunExecutingTask> select(TaskQuery query) {
        return null;
    }
}
