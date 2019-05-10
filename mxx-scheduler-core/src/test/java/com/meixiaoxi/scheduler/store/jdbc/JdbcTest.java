package com.meixiaoxi.scheduler.store.jdbc;

import com.meixiaoxi.scheduler.SchedulerConfig;
import com.meixiaoxi.scheduler.constant.ConfigKeys;
import com.meixiaoxi.scheduler.constant.ConfigSpiKeys;
import com.meixiaoxi.scheduler.store.jdbc.builder.SelectSql;
import junit.framework.TestCase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: JdbcTest
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-10 13:40:24
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-10    meixiaoxi       v1.0.0           创建
 */
public class JdbcTest extends TestCase {

    public void testGetSqlTemplate() throws SQLException {
        SchedulerConfig config = new SchedulerConfig();
        config.put(ConfigKeys.jdbc_driver_class_name, "org.h2.Driver");
        config.put(ConfigKeys.jdbc_url, "jdbc:h2:~/mem");
        config.put(ConfigKeys.jdbc_username, "meixiaoxi");
        config.put(ConfigKeys.jdbc_password, "123");
        config.put(ConfigSpiKeys.DATABASE_SPI, "h2");

        SqlTemplate sqlTemplate = SqlTemplateFactory.create(config);
        SelectSql selectSql = new SelectSql(sqlTemplate);
//        sqlTemplate.createTable("create table t_hello (" +
//                "id int primary key auto_increment," +
//                "username varchar(200)," +
//                "password varchar(20)" +
//                ")");
//        sqlTemplate.insert("insert into t_hello values (1,'meixiaoxi','1234')");
        System.out.println(selectSql.select().all().from().table("t_hello").list(rs -> {
            List<String> list = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                list.add(id + "-" + username + "-" + password);
            }
            return list;
        }));
    }
}
