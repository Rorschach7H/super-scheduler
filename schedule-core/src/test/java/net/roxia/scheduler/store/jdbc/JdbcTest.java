package net.roxia.scheduler.store.jdbc;

import junit.framework.TestCase;
import net.roxia.scheduler.SchedulerConfig;
import net.roxia.scheduler.constant.ConfigKeys;
import net.roxia.scheduler.constant.ConfigSpiKeys;
import net.roxia.scheduler.store.SqlTemplate;
import net.roxia.scheduler.store.SqlTemplateFactory;
import net.roxia.scheduler.store.builder.SelectSql;

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
        config.put(ConfigKeys.JDBC_DRIVER_CLASS_NAME, "org.h2.Driver");
        config.put(ConfigKeys.JDBC_URL, "jdbc:h2:~/mem");
        config.put(ConfigKeys.JDBC_USERNAME, "meixiaoxi");
        config.put(ConfigKeys.JDBC_PASSWORD, "123");
        config.put(ConfigSpiKeys.DATABASE_SPI, "h2");

        SqlTemplate sqlTemplate = SqlTemplateFactory.create(config);
        SelectSql selectSql = new SelectSql(sqlTemplate);
//        sqlTemplate.createTable("create table t_hello (" +
//                "id int primary key auto_increment," +
//                "username varchar(200)," +
//                "password varchar(20)" +
//                ")");
//        sqlTemplate.insert("insert into t_hello values (1,'meixiaoxi','1234')");
//        System.out.println(selectSql.select().all().from().table("t_hello").list(rs -> {
//            List<String> list = new ArrayList<>();
//            while (rs.next()) {
//                int id = rs.getInt(1);
//                String username = rs.getString(2);
//                String password = rs.getString(3);
//                list.add(id + "-" + username + "-" + password);
//            }
//            return list;
//        }));
    }
}
