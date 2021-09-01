package net.roxia.scheduler.constant;

/**
 * Copyright: Copyright =c 2018 meixiaoxi
 *
 * @ClassName: ConfigKeys
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-08 15:01:39
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-08    meixiaoxi       v1.0.0           创建
 */
public interface ConfigKeys {

    String JDBC_URL = "jdbc.url";
    String JDBC_USERNAME = "jdbc.username";
    String JDBC_PASSWORD = "jdbc.password";
    String JDBC_DRIVER_CLASS_NAME = "jdbc.driverClass";
    //连接只读数据库时配置为true， 保证安全
    String JDBC_READ_ONLY = "jdbc.readOnly";
    //等待连接池分配连接的最大时长（毫秒），超过这个时长还没可用的连接则发生SQLException， 缺省:30秒
    String JDBC_CONNECTION_TIMEOUT = "jdbc.connectionTimeout";
    //一个连接idle状态的最大时长（毫秒），超时则被释放（retired），缺省:10分钟
    String JDBC_IDLE_TIMEOUT = "jdbc.idleTimeout";
    //一个连接的生命时长（毫秒），超时而且没被使用则被释放（retired），缺省:30分钟，
    // 建议设置比数据库超时时长少30秒，参考MySQL wait_timeout参数（show variables like '%timeout%';）
    String JDBC_MAX_LIFE_TIME = "jdbc.maxLifetime";
    //连接池中允许的最大连接数。缺省值：10；推荐的公式：==core_count * 2 + effective_spindle_count
    String JDBC_MAXIMUM_POOL_SIZE = "jdbc.maximumPoolSize";
}
