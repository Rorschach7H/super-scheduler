package com.meixiaoxi.scheduler.redis;

import com.meixiaoxi.scheduler.SchedulerConfig;
import com.meixiaoxi.scheduler.constant.ConfigKeys;
import com.meixiaoxi.scheduler.constant.ConstantsUtil;
import com.meixiaoxi.scheduler.redis.exception.RedissonException;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.*;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: RedissonManager
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-03-01 16:08:26
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-03-01    meixiaoxi       v1.0.0           创建
 */
public final class RedissonFactory {

    private static final Object lock = new Object();

    private static RedissonClient redissonClient;

    /**
     * 初始化Redisson
     */
    private static void init(SchedulerConfig config) {
        try {
            Config redissonConfig = new Config();

            String address = config.getProperty(ConfigKeys.rdss_address);
            String sentinelAddresses = config.getProperty(ConfigKeys.rdss_sentinel_addresses);
            String masterName = config.getProperty(ConfigKeys.rdss_master_name);
            String masterAddress = config.getProperty(ConfigKeys.rdss_master_address);
            String slaveAddresses = config.getProperty(ConfigKeys.rdss_slave_addresses);
            String nodeAddresses = config.getProperty(ConfigKeys.rdss_node_addresses);

            Integer database = config.getProperty(ConfigKeys.rdss_database, 0, Integer.class);
            String password = config.getProperty(ConfigKeys.rdss_password);
            String clientName = config.getProperty(ConfigKeys.rdss_client_name);
            Integer idleConnectionTimeout =
                    config.getProperty(ConfigKeys.jdbc_connection_timeout, 10000, Integer.class);
            Integer timeout = config.getProperty(ConfigKeys.rdss_timeout, 3000, Integer.class);
            Integer connectTimeout =
                    config.getProperty(ConfigKeys.rdss_connect_timeout, 10000, Integer.class);

            Integer connectionMinimumIdleSize =
                    config.getProperty(ConfigKeys.rdss_connection_minimum_idle_size, 32, Integer.class);
            Integer connectionPoolSize =
                    config.getProperty(ConfigKeys.rdss_connection_pool_size, 64, Integer.class);
            Integer slaveConnectionMinimumIdleSize =
                    config.getProperty(ConfigKeys.rdss_slave_connection_minimum_idle_size, 32, Integer.class);
            Integer slaveConnectionPoolSize =
                    config.getProperty(ConfigKeys.rdss_slave_connection_pool_size, 64, Integer.class);
            Integer masterConnectionMinimumIdleSize =
                    config.getProperty(ConfigKeys.rdss_master_connection_minimum_idle_size, 32, Integer.class);
            Integer masterConnectionPoolSize =
                    config.getProperty(ConfigKeys.rdss_master_connection_pool_size, 64, Integer.class);

            if (StringUtils.isNotBlank(address)) {
                SingleServerConfig serverConfig = redissonConfig.useSingleServer().setAddress(address);
                serverConfig.setPassword(password);
                serverConfig.setDatabase(database);
                serverConfig.setClientName(clientName);
                serverConfig.setIdleConnectionTimeout(idleConnectionTimeout);
                serverConfig.setTimeout(timeout);
                serverConfig.setConnectTimeout(connectTimeout);
                serverConfig.setConnectionMinimumIdleSize(connectionMinimumIdleSize);
                serverConfig.setConnectionPoolSize(connectionPoolSize);
            } else if (StringUtils.isNotBlank(masterName) && StringUtils.isNotBlank(sentinelAddresses)) {
                SentinelServersConfig serversConfig = redissonConfig.useSentinelServers()
                        .addSentinelAddress(sentinelAddresses.split(ConstantsUtil.LINE_SEPARATOR));
                serversConfig.setDatabase(database);
                serversConfig.setPassword(password);
                serversConfig.setClientName(clientName);
                serversConfig.setIdleConnectionTimeout(idleConnectionTimeout);
                serversConfig.setTimeout(timeout);
                serversConfig.setConnectTimeout(connectTimeout);
                serversConfig.setSlaveConnectionMinimumIdleSize(slaveConnectionMinimumIdleSize);
                serversConfig.setSlaveConnectionPoolSize(slaveConnectionPoolSize);
                serversConfig.setMasterConnectionMinimumIdleSize(masterConnectionMinimumIdleSize);
                serversConfig.setMasterConnectionPoolSize(masterConnectionPoolSize);
            } else if (StringUtils.isNotBlank(masterAddress) && StringUtils.isNotBlank(slaveAddresses)) {
                MasterSlaveServersConfig masterSlaveServersConfig = redissonConfig.useMasterSlaveServers()
                        .setMasterAddress(masterAddress);
                masterSlaveServersConfig.addSlaveAddress(slaveAddresses.split(ConstantsUtil.CONFIG_SEPATOR));
                masterSlaveServersConfig.setDatabase(database);
                masterSlaveServersConfig.setPassword(password);
                masterSlaveServersConfig.setClientName(clientName);
                masterSlaveServersConfig.setIdleConnectionTimeout(idleConnectionTimeout);
                masterSlaveServersConfig.setTimeout(timeout);
                masterSlaveServersConfig.setConnectTimeout(connectTimeout);
                masterSlaveServersConfig.setSlaveConnectionMinimumIdleSize(slaveConnectionMinimumIdleSize);
                masterSlaveServersConfig.setSlaveConnectionPoolSize(slaveConnectionPoolSize);
                masterSlaveServersConfig.setMasterConnectionMinimumIdleSize(masterConnectionMinimumIdleSize);
                masterSlaveServersConfig.setMasterConnectionPoolSize(masterConnectionPoolSize);
            } else if (StringUtils.isNotBlank(nodeAddresses)) {
                Integer scanInterval = config.getProperty(ConfigKeys.rdss_scan_interval, Integer.class);
                ClusterServersConfig clusterServersConfig = redissonConfig.useClusterServers();
                clusterServersConfig.addNodeAddress(nodeAddresses.split(ConstantsUtil.CONFIG_SEPATOR));
                clusterServersConfig.setScanInterval(scanInterval);
                clusterServersConfig.setPassword(password);
                clusterServersConfig.setClientName(clientName);
                clusterServersConfig.setIdleConnectionTimeout(idleConnectionTimeout);
                clusterServersConfig.setTimeout(timeout);
                clusterServersConfig.setConnectTimeout(connectTimeout);
                clusterServersConfig.setSlaveConnectionMinimumIdleSize(slaveConnectionMinimumIdleSize);
                clusterServersConfig.setSlaveConnectionPoolSize(slaveConnectionPoolSize);
                clusterServersConfig.setMasterConnectionMinimumIdleSize(masterConnectionMinimumIdleSize);
                clusterServersConfig.setMasterConnectionPoolSize(masterConnectionPoolSize);
            }
            redissonClient = Redisson.create(redissonConfig);
        } catch (Exception e) {
            throw new RuntimeException("连接redis出现异常", e);
        }
    }

    /**
     * 获取Redisson的实例对象（单例）
     *
     * @return
     */
    public static RedissonClient getRedissonClient(SchedulerConfig redisConfig) {
        if (redissonClient == null) {
            synchronized (lock) {
                if (redissonClient == null) {
                    init(redisConfig);
                }
            }
        }
        return redissonClient;
    }

    public static RedissonClient getRedissonClient() {
        if (redissonClient == null) {
            throw new RedissonException("redis接连器未初始化！请检查是否已经连接redis成功！");
        }
        return redissonClient;
    }
}
