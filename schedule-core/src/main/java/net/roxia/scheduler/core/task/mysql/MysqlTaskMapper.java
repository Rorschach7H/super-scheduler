package net.roxia.scheduler.core.task.mysql;

import net.roxia.scheduler.SchedulerConfig;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: MysqlTaskOperate
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-04-30 16:48:10
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-04-30    meixiaoxi       v1.0.0           创建
 */
public class MysqlTaskMapper extends AbstractMysqlTaskMapper {

    public MysqlTaskMapper(SchedulerConfig config) {
        super(config);
    }
}
