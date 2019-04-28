package com.meixiaoxi.scheduler.store.jdbc;

import com.meixiaoxi.scheduler.common.FileUtil;
import com.meixiaoxi.scheduler.core.Config;
import com.meixiaoxi.scheduler.core.constant.Constants;
import com.meixiaoxi.scheduler.core.constant.ExtConfig;
import com.meixiaoxi.scheduler.store.jdbc.exception.JdbcException;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Robert HG (254963746@qq.com) on 5/19/15.
 */
public abstract class JdbcAbstractAccess {

    private SqlTemplate sqlTemplate;
    private Config config;

    public JdbcAbstractAccess(Config config) {
        this.config = config;
        this.sqlTemplate = SqlTemplateFactory.create(config);
    }

    public SqlTemplate getSqlTemplate() {
        return sqlTemplate;
    }

    protected String readSqlFile(String path) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
        try {
            return FileUtil.read(is, Constants.CHARSET);
        } catch (IOException e) {
            throw new RuntimeException("Read sql file : [" + path + "] error ", e);
        }
    }

    protected String readSqlFile(String path, String tableName) {
        String sql = readSqlFile(path);
        return sql.replace("{tableName}", tableName);
    }

    protected void createTable(String sql) throws JdbcException {
        if (config.getParameter(ExtConfig.NEED_CREATE_DB_TABLE, true)) {
            try {
                getSqlTemplate().createTable(sql);
            } catch (Exception e) {
                throw new JdbcException("Create table error, sql=" + sql, e);
            }
        }
    }
}
