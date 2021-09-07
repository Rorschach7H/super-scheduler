package net.roxia.scheduler.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import net.roxia.scheduler.persistence.annotation.Table;

import java.util.Date;

@Table("run_task")
@Setter
@Getter
public class RunTask extends AbstractEntity {
    private Long id;
    private String taskName;
    private Long objectId;
    private String taskGroup;
    private Date executeTime;
    private Integer sameExecuteTimeQueue;
    private Byte taskType;
    private Integer failedCount;
    private String remarks;
    private Byte executeState;
    private String executor;
    private Byte status;
    private Date createTime;
    private Date updateTime;
}