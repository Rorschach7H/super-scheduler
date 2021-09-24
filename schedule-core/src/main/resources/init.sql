CREATE TABLE `ror_client_group`
(
    `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `group`       varchar(128)        NOT NULL DEFAULT '' COMMENT '客户端组',
    `access_key`  varchar(128)        NOT NULL DEFAULT '' COMMENT '客户端准入密钥',
    `create_time` timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `modify_time` timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `state`       int(1)              NOT NULL DEFAULT 1 COMMENT '0-有效 1-无效',
    PRIMARY KEY (`id`),
    KEY `ix_client_group` (`group`)
) COMMENT ='客户端信息表';

create table ror_client_active
(
    `id`           bigint(20)  not null primary key auto_increment comment '自增ID',
    `machine_id`   varchar(64) not null comment '连接客户端机器ID',
    `group`        varchar(64) not null comment '客户端组',
    `ip`           varchar(32) not null comment '接入方ip地址',
    `active_time`  datetime    not null comment '上线时间',
    `offline_time` datetime    not null DEFAULT '1970-01-01 00:00:00' comment '下线时间',
    `active`       int(1)      NOT NULL DEFAULT 0 COMMENT '0-在线 1-下线',
    `state`        tinyint(3)  not null default 1
) engine = InnoDB
  auto_increment = 1
  default charset = utf8mb4 comment ='客户在线信息表';