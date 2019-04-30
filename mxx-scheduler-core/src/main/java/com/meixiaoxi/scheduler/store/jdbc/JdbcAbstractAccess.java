package com.meixiaoxi.scheduler.store.jdbc;

import com.meixiaoxi.scheduler.common.FileUtil;
import com.meixiaoxi.scheduler.constant.ConstantsUtil;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Robert HG (254963746@qq.com) on 5/19/15.
 */
public abstract class JdbcAbstractAccess {

    private SqlTemplate sqlTemplate;
    private DataSource dataSource;

    public JdbcAbstractAccess(DataSource dataSource) {
        this.dataSource = dataSource;
        this.sqlTemplate = SqlTemplateFactory.create(dataSource);
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
