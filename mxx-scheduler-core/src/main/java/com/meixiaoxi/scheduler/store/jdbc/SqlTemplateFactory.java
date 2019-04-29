package com.meixiaoxi.scheduler.store.jdbc;

import javax.sql.DataSource;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 保证一个DataSource对应一个SqlTemplate
 *
 * @author Robert HG (254963746@qq.com) on 3/8/16.
 */
public class SqlTemplateFactory {

    private static final ConcurrentMap<DataSource, SqlTemplate> HOLDER = new ConcurrentHashMap<DataSource, SqlTemplate>();

    public static SqlTemplate create(DataSource dataSource) {
        SqlTemplate sqlTemplate = HOLDER.get(dataSource);

        if (sqlTemplate != null) {
            return sqlTemplate;
        }
        synchronized (HOLDER) {
            sqlTemplate = HOLDER.get(dataSource);
            if (sqlTemplate != null) {
                return sqlTemplate;
            }
            sqlTemplate = new SqlTemplateImpl(dataSource);
            HOLDER.putIfAbsent(dataSource, sqlTemplate);
            return sqlTemplate;
        }
    }

}
