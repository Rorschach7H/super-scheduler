package net.roxia.scheduler.persistence.mapper;

import net.roxia.scheduler.store.AbstractEntity;
import net.roxia.scheduler.persistence.entity.ClientGroup;
import net.roxia.scheduler.store.builder.SelectSql;

/**
 * @ClassName ClientGroupMapper
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/7 14:33
 **/
public class ClientGroupMapper extends AbstractMapper<ClientGroup> {

    public ClientGroup selectByGroup(String group) {
        return new SelectSql(getSqlTemplate())
                .select()
                .columns(AbstractEntity.columns(ClientGroup.class))
                .from()
                .table(AbstractEntity.tableName(ClientGroup.class))
                .where("`group`=?", group)
                .and("`state`=?", 1)
                .single(ClientGroup.class);

    }

    public int selectCountByGroup(String group) {
        return new SelectSql(getSqlTemplate())
                .select()
                .count()
                .from()
                .table(AbstractEntity.tableName(ClientGroup.class))
                .where("`group`=?", group)
                .and("`state`=?", 1)
                .single(rs -> {
                    rs.next();
                    return rs.getInt(1);
                });

    }
}
