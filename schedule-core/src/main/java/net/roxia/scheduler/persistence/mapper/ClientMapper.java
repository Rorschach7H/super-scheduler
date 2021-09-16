package net.roxia.scheduler.persistence.mapper;

import net.roxia.scheduler.store.AbstractEntity;
import net.roxia.scheduler.persistence.entity.ClientEntity;
import net.roxia.scheduler.store.builder.SelectSql;
import net.roxia.scheduler.store.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName ClientMapper
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/7 14:33
 **/
public class ClientMapper extends AbstractMapper<ClientEntity> {

    public ClientEntity selectByGroup(String group) {
        return new SelectSql(getSqlTemplate())
                .select()
                .columns(AbstractEntity.columns(ClientEntity.class))
                .from()
                .table(AbstractEntity.tableName(ClientEntity.class))
                .where("`group`=?", group)
                .and("`state`=?", 1)
                .single(ClientEntity.class);

    }

    public int selectCountByGroup(String group) {
        return new SelectSql(getSqlTemplate())
                .select()
                .count()
                .from()
                .table(AbstractEntity.tableName(ClientEntity.class))
                .where("`group`=?", group)
                .and("`state`=?", 1)
                .single(rs -> {
                    rs.next();
                    return rs.getInt(1);
                });

    }
}
