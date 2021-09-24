package net.roxia.scheduler.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import net.roxia.scheduler.persistence.annotation.PrimaryKey;
import net.roxia.scheduler.persistence.annotation.Table;
import net.roxia.scheduler.store.AbstractEntity;

import java.util.Date;

/**
 * @ClassName ClientGroup
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/7 11:11
 **/
@Table("ror_client_group")
@Setter
@Getter
public class ClientGroup extends AbstractEntity {
    @PrimaryKey
    private Long id;
    private String group;
    private String accessKey;
    private Date createTime;
    private Date modifyTime;
    private Integer state;
}
