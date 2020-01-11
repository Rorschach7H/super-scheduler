package net.roxia.scheduler.store.builder;

import net.roxia.scheduler.store.SqlTemplate;
import net.roxia.scheduler.store.exception.JdbcException;

/**
 * @author Robert HG (254963746@qq.com) on 3/9/16.
 */
public class DropTableSql {

    private SqlTemplate sqlTemplate;
    private StringBuilder sql = new StringBuilder();

    public DropTableSql(SqlTemplate sqlTemplate) {
        this.sqlTemplate = sqlTemplate;
    }

    public DropTableSql drop(String table) {
        sql.append("DROP TABLE IF EXISTS ").append(table);
        return this;
    }

    public boolean doDrop() {

        String finalSQL = sql.toString();

        try {
            sqlTemplate.update(sql.toString());
        } catch (Exception e) {
            throw new JdbcException("Drop Table Error:" + finalSQL, e);
        }
        return true;
    }

}
