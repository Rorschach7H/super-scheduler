package net.roxia.scheduler.persistence.mapper;

import net.roxia.scheduler.core.task.domain.TaskQuery;
import net.roxia.scheduler.persistence.entity.AbstractEntity;
import net.roxia.scheduler.store.JdbcAbstractAccess;
import net.roxia.scheduler.store.builder.DeleteSql;
import net.roxia.scheduler.store.builder.InsertSql;
import net.roxia.scheduler.store.builder.SelectSql;
import net.roxia.scheduler.store.builder.UpdateSql;
import org.apache.commons.collections4.CollectionUtils;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: AbstractMapper
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-03-05 09:34:41
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-03-05    meixiaoxi       v1.0.0           创建
 */
public class AbstractMapper<T extends AbstractEntity> extends JdbcAbstractAccess implements Mapper<T> {

    private final Class<T> entityClass;

    public AbstractMapper() {
        entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public boolean insertSelective(T task) {
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
    public boolean insert(T task) {

        InsertSql insertSql = new InsertSql(getSqlTemplate())
                .insert(task.tableName())
                .columns(task.columns())
                .values(task.values());

        return insertSql.doInsert() == 1;
    }

    @Override
    public boolean insertBatch(List<T> tasks) {

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
    public boolean update(AbstractEntity task) {
        UpdateSql updateSql = new UpdateSql(getSqlTemplate())
                .update().table(task.tableName());
        Map<String, Object> keyValueMap = task.keyValueMap(true);
        keyValueMap.forEach(updateSql::set);
        updateSql.where(task.primaryKey() + "=?", task.primaryValue());
        return updateSql.doUpdate() == 1;
    }

    @Override
    public boolean updateBatch(List<T> tasks) {
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
                .table(T.tableName(entityClass))
                .where(T.primaryKey(entityClass) + "=?", taskId)
                .doDelete() == 1;
    }

    @Override
    public T select(Long id) {
        return new SelectSql(getSqlTemplate())
                .select()
                .columns(AbstractEntity.columns(entityClass))
                .from()
                .table(AbstractEntity.tableName(entityClass))
                .where(AbstractEntity.primaryKey(entityClass) + "=?", id)
                .single();

    }

    @Override
    public List<T> select(TaskQuery query) {
        return null;
    }
}
