package net.roxia.scheduler.persistence;

import net.roxia.scheduler.SchedulerConfig;
import net.roxia.scheduler.constant.ConfigKeys;
import net.roxia.scheduler.store.SqlTemplate;
import net.roxia.scheduler.store.SqlTemplateFactory;
import net.roxia.scheduler.store.datasource.DataSourceFactory;
import net.roxia.scheduler.transcation.TransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

/**
 * @ClassName PersistenceContext
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/7 14:44
 **/
public class PersistenceContext {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 连接数据库的数据源
     */
    private final DataSource dataSource;
    /**
     * 数据库事务
     */
    private final TransactionManager transactionManager;

    private final SqlTemplate sqlTemplate;

    public PersistenceContext(SchedulerConfig config) {
        log.info("init datasource with config: url={}", config.getProperty(ConfigKeys.JDBC_URL));
        this.dataSource = DataSourceFactory.get(config);
        log.info("init transactionManager");
        this.transactionManager = new TransactionManager(dataSource);
        log.info("init sqlTemplate");
        this.sqlTemplate = SqlTemplateFactory.create(config);
    }

    public SqlTemplate getSqlTemplate() {
        return sqlTemplate;
    }

    public SchedulerConfig getConfig() {
        return null;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
