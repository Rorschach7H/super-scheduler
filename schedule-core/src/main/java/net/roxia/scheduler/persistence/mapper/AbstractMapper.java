package net.roxia.scheduler.persistence.mapper;

import net.roxia.scheduler.holder.PersistenceContextHolder;
import net.roxia.scheduler.store.AbstractEntity;
import net.roxia.scheduler.store.JdbcAbstractAccess;
import net.roxia.scheduler.store.SqlTemplate;
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

    private final SqlTemplate sqlTemplate;

    private final Class<T> entityClass;

    public AbstractMapper() {
        entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        sqlTemplate = PersistenceContextHolder.getSqlTemplate();
    }

    @Override
    public boolean insertSelective(T entity) {
        Map<String, Object> keyValueMap = entity.keyValueMap(true);
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
        InsertSql insertSql = new InsertSql(sqlTemplate)
                .insert(entity.tableName())
                .columns(columnArray)
                .values(values.toArray());

        return insertSql.doInsert() == 1;
    }

    @Override
    public boolean insert(T entity) {

        InsertSql insertSql = new InsertSql(sqlTemplate)
                .insert(entity.tableName())
                .columns(entity.columns())
                .values(entity.values());

        return insertSql.doInsert() == 1;
    }

    @Override
    public boolean insertBatch(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        InsertSql insertSql = new InsertSql(sqlTemplate)
                .insert(list.get(0).tableName())
                .columns(list.get(0).columns());
        list.forEach(task -> insertSql.values(task.values()));

        return insertSql.doInsert() == list.size();
    }

    @Override
    public boolean update(T entity) {
        UpdateSql updateSql = new UpdateSql(sqlTemplate)
                .update().table(entity.tableName());
        Map<String, Object> keyValueMap = entity.keyValueMap(true);
        keyValueMap.forEach(updateSql::set);
        updateSql.where(entity.primaryKey() + "=?", entity.primaryValue());
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
        return new DeleteSql(sqlTemplate)
                .delete()
                .from()
                .table(T.tableName(entityClass))
                .where(T.primaryKey(entityClass) + "=?", taskId)
                .doDelete() == 1;
    }

    @Override
    public T select(Long id) {
        return new SelectSql(sqlTemplate)
                .select()
                .columns(AbstractEntity.columns(entityClass))
                .from()
                .table(AbstractEntity.tableName(entityClass))
                .where(AbstractEntity.primaryKey(entityClass) + "=?", id)
                .single(entityClass);

    }

    @Override
    public List<T> select(T entity) {
        Map<String, Object> keyValueMap = entity.keyValueMap(true);
        return null;
    }

    public SqlTemplate getSqlTemplate() {
        return sqlTemplate;
    }
}
