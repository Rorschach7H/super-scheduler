package net.roxia.scheduler.store;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: ConnectionFactory
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-16 14:01:18
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-16    meixiaoxi       v1.0.0           创建
 */
public class ConnectionFactory {

    private static ThreadLocal<Connection> connectionTreadLocal = new ThreadLocal<>();

    /**
     * 获得连接对象方法
     *
     * @return 连接对象
     */
    public static Connection getConnection(DataSource dataSource) {
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
