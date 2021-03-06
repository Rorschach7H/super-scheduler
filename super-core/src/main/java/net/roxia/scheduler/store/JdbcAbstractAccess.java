package net.roxia.scheduler.store;

import net.roxia.scheduler.SchedulerConfig;
import net.roxia.scheduler.common.utils.FileUtil;
import net.roxia.scheduler.constant.ConstantsUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Robert HG (254963746@qq.com) on 5/19/15.
 */
public abstract class JdbcAbstractAccess {

    private SqlTemplate sqlTemplate;

    public JdbcAbstractAccess(SchedulerConfig schedulerConfig) {
        this.sqlTemplate = SqlTemplateFactory.create(schedulerConfig);
    }

    public SqlTemplate getSqlTemplate() {
        return sqlTemplate;
    }

    protected String readSqlFile(String path) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
        try {
            return FileUtil.read(is, ConstantsUtil.CHARSET);
        } catch (IOException e) {
            throw new RuntimeException("Read sql file : [" + path + "] error ", e);
        }
    }

    protected String readSqlFile(String path, String tableName) {
        String sql = readSqlFile(path);
        return sql.replace("{tableName}", tableName);
    }
}
