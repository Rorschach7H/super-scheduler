package net.roxia.scheduler.store;

import net.roxia.scheduler.store.dbutils.DbRunner;
import net.roxia.scheduler.store.dbutils.ResultSetHandler;
import net.roxia.scheduler.store.dbutils.ScalarHandler;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Robert HG (254963746@qq.com) on 5/20/15.
 */
class SqlTemplateImpl implements SqlTemplate {

    private final static DbRunner dbRunner = new DbRunner();
    private final DataSource dataSource;

    public SqlTemplateImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> T execute(boolean isReadOnly, SqlExecutor<T> executor) throws SQLException {
        Connection conn = null;
        conn = ConnectionFactory.getConnection(dataSource);
        if (isReadOnly) {
            conn.setReadOnly(true);
        }
        return executor.run(conn);
    }

    public void createTable(final String sql) throws SQLException {
        update(sql);
    }

    @Override
    public int[] batchInsert(String sql, Object[][] params) throws SQLException {
        return batchUpdate(sql, params);
    }

    public int[] batchUpdate(final Connection conn, final String sql, final Object[][] params) throws SQLException {
        return dbRunner.batch(conn, sql, params);
    }

    public int[] batchUpdate(final String sql, final Object[][] params) throws SQLException {
        return execute(false, new SqlExecutor<int[]>() {
            @Override
            public int[] run(Connection conn) throws SQLException {
                return batchUpdate(conn, sql, params);
            }
        });
    }

    @Override
    public int insert(String sql, Object... params) throws SQLException {
        return update(sql, params);
    }

    public int update(final String sql, final Object... params) throws SQLException {
        return execute(false, conn -> update(conn, sql, params));
    }

    @Override
    public int delete(String sql, Object... params) throws SQLException {
        return update(sql, params);
    }

    public int update(final Connection conn, final String sql, final Object... params) throws SQLException {
        return dbRunner.update(conn, sql, params);
    }

    public <T> T query(final String sql, final ResultSetHandler<T> rsh, final Object... params) throws SQLException {
        return execute(true, new SqlExecutor<T>() {
            @Override
            public T run(Connection conn) throws SQLException {
                return query(conn, sql, rsh, params);
            }
        });
    }

    public <T> T query(final Connection conn, final String sql, final ResultSetHandler<T> rsh, final Object... params) throws SQLException {
        return dbRunner.query(conn, sql, rsh, params);
    }

    public <T> T queryForValue(final String sql, final Object... params) throws SQLException {
        return query(sql, new ScalarHandler<T>(), params);
    }

    public <T> T queryForValue(final Connection conn, final String sql, final Object... params) throws SQLException {
        return query(conn, sql, new ScalarHandler<T>(), params);
    }

    private SqlExecutor<Void> getWrapperExecutor(final SqlExecutorVoid voidExecutor) {
        return conn -> {
            voidExecutor.run(conn);
            return null;
        };
    }
}
