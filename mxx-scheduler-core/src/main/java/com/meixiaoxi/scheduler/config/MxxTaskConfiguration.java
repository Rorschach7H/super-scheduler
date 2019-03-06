package com.meixiaoxi.scheduler.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

/**
 * Created by HongJ on 2018/05/16.
 */
@Configuration
@PropertySource("classpath:db.properties")
@MapperScan(basePackages = {"com.meixiaoxi.scheduler.persistence"})
@ComponentScan(basePackages = {"com.meixiaoxi.scheduler"})
public class MxxTaskConfiguration {

    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;


    /**
     * 主节点名
     */
    @Value("${redis.masterName}")
    private String masterName;
    /**
     * 单例redis连接地址 ip:port
     */
    @Value("${redis.masterName}")
    private String redisUrl;
    /**
     * 单例redis连接密码
     */
    @Value("${redis.password}")
    private String redisPwd;

    /**
     * 集群redis连接url [ip1:port1,ip2:port2,ip3:port3]
     */
    @Value("${redis.redisUrls}")
    private String[] redisUrls;
    /**
     * 等待节点回复命令的时间。该时间从命令发送成功时开始计时。默认:3000
     */
    @Value("${redis.timeout}")
    private int timeout;
    /**
     * 如果尝试达到 retryAttempts（命令失败重试次数） 仍然不能将命令发送至某个指定的节点时，将抛出错误。
     * 如果尝试在此限制之内发送成功，则开始启用 timeout（命令等待超时） 计时。默认值：3
     */
    @Value("${redis.retryAttempts}")
    private int retryAttempts;
    /**
     * 在一条命令发送失败以后，等待重试发送的时间间隔。时间单位是毫秒。默认值：1500
     */
    @Value("${redis.retryInterval}")
    private int retryInterval;

    @Bean
    public CommonConfig config() {
        CommonConfig config = new CommonConfig();

        config.setMasterName(masterName);
        config.setRedisUrls(redisUrls);
        config.setRedisUrl(redisUrl);
        config.setPwd(redisPwd);
        config.setRetryAttempts(retryAttempts);

        return config;
    }

    /**
     * 配置数据源，这里使用的是阿里的DruidDataSource
     *
     * @return
     */
    @Bean
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    /**
     * 创建sqlSessionFactory
     */
    @Bean
    public SqlSessionFactoryBean factory() throws IOException {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource());
        factory.setTypeAliasesPackage("com.meixiaoxi.scheduler.persistence.entity");
        factory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*Mapper.xml"));
        return factory;

    }
}