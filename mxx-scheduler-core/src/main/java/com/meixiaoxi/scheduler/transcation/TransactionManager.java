package com.meixiaoxi.scheduler.transcation;

import com.meixiaoxi.scheduler.factory.AspectInterceptor;
import com.meixiaoxi.scheduler.store.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager implements AspectInterceptor {

    private static final Logger log = LoggerFactory.getLogger(TransactionManager.class);

    private DataSource dataSource;

    public TransactionManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void beforeDo() {
        log.info("start transaction ...");
        startTransaction();
    }

    @Override
    public void afterDo() {
        log.info("end transaction ...");
        commit();
    }

    @Override
    public void exceptDo() {
        log.info("service logic have Exception! rollback transaction ...");
        rollback();
    }

    private void startTransaction() {
        Connection connection = ConnectionFactory.getConnection(dataSource);
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void commit() {
        Connection connection = null;
        try {
            connection = ConnectionFactory.getConnection(dataSource);
            connection.commit();
        } catch (SQLException e) {
            close(connection);
            throw new RuntimeException(e);
        }
    }

    private void rollback() {
        Connection connection = null;
        try {
            connection = ConnectionFactory.getConnection(dataSource);
            connection.rollback();
        } catch (SQLException e) {
            close(connection);
            throw new RuntimeException(e);
        }
    }

    private void close(Connection connection) {
        try {
            if (connection != null) {
                if (connection.isReadOnly()) {
                    connection.setReadOnly(false);  // restore NOT readOnly before return to pool
                }
                connection.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}