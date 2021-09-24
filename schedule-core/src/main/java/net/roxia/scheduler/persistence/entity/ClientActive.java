package net.roxia.scheduler.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import net.roxia.scheduler.persistence.annotation.PrimaryKey;
import net.roxia.scheduler.persistence.annotation.Table;
import net.roxia.scheduler.store.AbstractEntity;

import java.util.Date;

/**
 * @ClassName ClientActive
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/7 11:11
 **/
@Table("ror_client_active")
@Setter
@Getter
public class ClientActive extends AbstractEntity {
    @PrimaryKey
    private Long id;
    private String machineId;
    private String group;
    private String ip;
    private Date activeTime;
    private Date offlineTime;
    private Integer active;
    private Integer state;
}
