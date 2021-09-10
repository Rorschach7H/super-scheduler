package net.roxia.scheduler.store;

import net.roxia.scheduler.common.utils.FileUtil;
import net.roxia.scheduler.constant.ConstantsUtil;
import net.roxia.scheduler.holder.PersistenceContextHolder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Robert HG (254963746@qq.com) on 5/19/15.
 */
public abstract class JdbcAbstractAccess {

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
