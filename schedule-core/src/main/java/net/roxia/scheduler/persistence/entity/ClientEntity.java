package net.roxia.scheduler.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import net.roxia.scheduler.persistence.annotation.PrimaryKey;
import net.roxia.scheduler.persistence.annotation.Table;

import java.util.Date;

/**
 * @ClassName ClientEntity
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/7 11:11
 **/
@Table("ror_client")
@Setter
@Getter
public class ClientEntity extends AbstractEntity {
    @PrimaryKey
    private Long id;
    private String clientId;
    private String clientGroup;
    private String accessKey;
    private String ip;
    private Date createTime;
    private Date modifyTime;
    private int state;
}
