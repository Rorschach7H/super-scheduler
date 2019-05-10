package com.meixiaoxi.scheduler.constant;

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

    String jdbc_url = "jdbc.url";
    String jdbc_username = "jdbc.username";
    String jdbc_password = "jdbc.password";
    String jdbc_driver_class_name = "jdbc.driverClass";
    //连接只读数据库时配置为true， 保证安全
    String jdbc_read_only = "jdbc.readOnly";
    //等待连接池分配连接的最大时长（毫秒），超过这个时长还没可用的连接则发生SQLException， 缺省:30秒
    String jdbc_connection_timeout = "jdbc.connectionTimeout";
    //一个连接idle状态的最大时长（毫秒），超时则被释放（retired），缺省:10分钟
    String jdbc_idle_timeout = "jdbc.idleTimeout";
    //一个连接的生命时长（毫秒），超时而且没被使用则被释放（retired），缺省:30分钟，
    // 建议设置比数据库超时时长少30秒，参考MySQL wait_timeout参数（show variables like '%timeout%';）
    String jdbc_max_life_time = "jdbc.maxLifetime";
    //连接池中允许的最大连接数。缺省值：10；推荐的公式：==core_count * 2 + effective_spindle_count
    String jdbc_maximum_pool_size = "jdbc.maximumPoolSize";

    //redisson连接redis统一配置
    String rdss_database = "database";
    String rdss_password = "password";
    String rdss_client_name = "clientName";
    String rdss_timeout = "timeout";
    String rdss_connect_timeout = "connectTimeout";
    String rdss_connection_minimum_idle_size = "connectionMinimumIdleSize";
    String rdss_connection_pool_size = "connectionPoolSize";
    String rdss_slave_connection_minimum_idle_size = "slaveConnectionMinimumIdleSize";
    String rdss_slave_connection_pool_size = "slaveConnectionPoolSize";
    String rdss_master_connection_minimum_idle_size = "masterConnectionMinimumIdleSize";
    String rdss_master_connection_pool_size = "masterConnectionPoolSize";
    //redisson连接单节点rediss配置key
    String rdss_address = "address";
    //redisson连接哨兵模式redis配置key
    String rdss_sentinel_addresses = "sentinelAddresses";
    String rdss_master_name = "masterName";
    //redisson连接主从模式redis配置key
    String rdss_slave_addresses = "slaveAddresses";
    String rdss_master_address = "masterAddress";
    //redisson连接集群模式redis配置key
    String rdss_node_addresses = "nodeAddresses";
    String rdss_scan_interval = "scanInterval";
}
