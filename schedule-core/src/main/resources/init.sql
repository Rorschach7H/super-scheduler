CREATE TABLE `ror_client`
(
    `id`           bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `client_group` varchar(128)        NOT NULL DEFAULT '' COMMENT '客户端组',
    `access_key`   varchar(128)        NOT NULL DEFAULT '' COMMENT '客户端准入密钥',
    `create_time`  timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modify_time`  timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `state`        int(1)              NOT NULL DEFAULT '0' COMMENT '0-未删除 1-已删除',
    PRIMARY KEY (`id`),
    KEY `ix_client_group` (`client_group`)
) COMMENT ='客户端信息表';

create table hi_reg_client
(
    `id`           bigint(20)  not null primary key auto_increment comment '自增ID',
    `machine_id`   varchar(64) not null comment '连接客户端机器ID',
    `access_key`   varchar(64) not null comment '注册的接入key',
    `ip`           varchar(32) not null comment '接入方ip地址',
    `online_time`  datetime    not null comment '上线时间',
    `offline_time` datetime    not null comment '下线时间',
    `create_time`  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modify_time`  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `state`        tinyint(3)  not null default 1
) engine = InnoDB
  auto_increment = 1
  default charset = utf8mb4 comment ='历史注册客户端表';