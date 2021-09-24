package net.roxia.scheduler.persistence.mapper;

import net.roxia.scheduler.persistence.entity.ClientActive;
import net.roxia.scheduler.store.AbstractEntity;
import net.roxia.scheduler.store.builder.SelectSql;

/**
 * @ClassName ClientActiveMapper
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/24 11:42
 **/
public class ClientActiveMapper extends AbstractMapper<ClientActive> {

    public int selectCountByMachineId(String machineId) {
        return new SelectSql(getSqlTemplate())
                .select()
                .count()
                .from()
                .table(AbstractEntity.tableName(ClientActive.class))
                .where("`machine_id`=?", machineId)
                .and("`state`=?", 1)
                .single(rs -> {
                    rs.next();
                    return rs.getInt(1);
                });

    }

    public ClientActive selectByMachineId(String machineId) {
        return new SelectSql(getSqlTemplate())
                .select()
                .columns(AbstractEntity.columns(ClientActive.class))
                .from()
                .table(AbstractEntity.tableName(ClientActive.class))
                .where("`machine_id`=?", machineId)
                .and("`state`=?", 1)
                .single(ClientActive.class);
    }
}
