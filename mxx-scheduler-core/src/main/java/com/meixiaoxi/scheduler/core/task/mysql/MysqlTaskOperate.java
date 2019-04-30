package com.meixiaoxi.scheduler.core.task.mysql;

import com.meixiaoxi.scheduler.core.task.TaskOperate;

import javax.sql.DataSource;

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
public class MysqlTaskOperate extends AbstractMysqlTaskOperate implements TaskOperate {

    public MysqlTaskOperate(DataSource dataSource) {
        super(dataSource);
    }
}
