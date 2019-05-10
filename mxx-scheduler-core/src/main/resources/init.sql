create table ru_executing_task
(
    id            bigint(20)   not null primary key auto_increment comment '自增ID',
    object_id     varchar(200) not null comment '执行对象ID(考虑到对象ID类型未知，这里使用字符串兼容)',
    group_key     varchar(20)  not null comment '任务组名',
    cron          varchar(20)  not null default '' comment 'cron表达式',
    period        int(11)      not null default 0 comment '任务执行间隔',
    time_unit     tinyint(3)   not null default 0 comment '时间单位 0|秒,1|分,2|时',
    initial_delay int(11)      not null default 0 comment '任务挨靠延时',
    access_key    varchar(20)  not null comment '生成任务的接入方key',
    execute_time  datetime     not null comment '执行时间',
    execute_queue bigint(20)   not null default 0 comment '在相同时间(精确至秒)内的执行队列',
    failures      int(11)      not null default 0 comment '已失败次数',
    max_failures  int(11)      not null default 3 comment '最大失败次数',
    execute_state tinyint(3)   not null default 0 comment '运行状态',
    create_time   datetime     not null default current_timestamp
) engine = InnoDB
  auto_increment = 1
  default charset = utf8mb4 comment ='任务队列表';

create table hi_task_group
(
    id          bigint(20)  not null primary key auto_increment comment '自增ID',
    name        varchar(20) not null comment '任务组名',
    alias       varchar(20) not null comment '任务组别名',
    group_key   varchar(20) not null comment '任务组key',
    create_time datetime    not null default current_timestamp comment '创建时间',
    state       tinyint(3)  not null default 1
) engine = InnoDB
  auto_increment = 1
  default charset = utf8mb4 comment ='当前运行状态下的任务组';

create table hi_executed_task
(
    id            bigint(20)   not null primary key auto_increment comment '自增ID',
    object_id     varchar(200) not null comment '执行对象ID(考虑到对象ID类型未知，这里使用字符串兼容)',
    group_key     varchar(20)  not null comment '任务组',
    cron          varchar(20)  not null default '',
    period        int(11)      not null default 0 comment '任务执行间隔',
    time_unit     tinyint(3)   not null default 0 comment '时间单位 0|秒,1|分,2|时',
    initial_delay int(11)      not null default 0 comment '任务挨靠延时',
    access_key    varchar(20)  not null comment '生成任务的接入方key',
    execute_time  datetime     not null comment '执行时间',
    execute_queue bigint(20)   not null default 0 comment '在相同时间(精确至秒)内的执行队列',
    failures      int(11)      not null default 0 comment '已失败次数',
    max_failures  int(11)      not null default 3 comment '最大失败次数',
    description   varchar(200) not null default '' comment '任务描述',
    execute_state tinyint(3)   not null default 0 comment '运行状态',
    create_time   datetime     not null default current_timestamp comment '创建时间',
    state         tinyint(3)   not null default 1
) engine = InnoDB
  auto_increment = 1
  default charset = utf8mb4 comment ='历史任务表';

create table hi_register_client
(
    id           bigint(20)  not null primary key auto_increment comment '自增ID',
    access_key   varchar(20) not null comment '注册的接入key',
    ip           varchar(20) not null comment '接入方ip地址',
    create_time  datetime    not null default current_timestamp comment '创建时间',
    access_start datetime    not null comment '注册时间',
    access_end   datetime    not null comment '注销时间',
    state        tinyint(3)  not null default 1
) engine = InnoDB
  auto_increment = 1
  default charset = utf8mb4 comment ='历史注册客户端表';
