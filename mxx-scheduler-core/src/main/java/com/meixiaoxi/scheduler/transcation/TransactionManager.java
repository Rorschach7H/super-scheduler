package com.meixiaoxi.scheduler.transcation;

import com.meixiaoxi.scheduler.aspect.AspectInterceptor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager implements AspectInterceptor {

    private static final ThreadLocal<Connection> connectionTreadLocal = new ThreadLocal<>();
    private DataSource dataSource;

    public TransactionManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void beforeDo() {
        System.out.println("start do");
    }

    @Override
    public void afterDo() {
        System.out.println("after do");
    }

    private void startTransaction() {
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void commit() {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.commit();
        } catch (SQLException e) {
            close(connection);
            throw new RuntimeException(e);
        }
    }

    private void rollback() {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.rollback();
        } catch (SQLException e) {
            close(connection);
            throw new RuntimeException(e);
        }
    }

    private void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 获得连接对象方法
     *
     * @return 连接对象
     */
    private Connection getConnection() {
        try {
            if (connectionTreadLocal.get() == null) {
                Connection connection = dataSource.getConnection();
                connectionTreadLocal.set(connection);
                return connection;
            }
            return connectionTreadLocal.get();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}