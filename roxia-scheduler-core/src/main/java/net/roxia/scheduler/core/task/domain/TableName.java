package net.roxia.scheduler.core.task.domain;

import java.util.Arrays;
import java.util.Optional;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: TableName
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-14 10:23:16
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-14    meixiaoxi       v1.0.0           创建
 */
public enum TableName {

    RUN_EXECUTING_TASK(RunExecutingTask.class, "ru_executing_task"),
    ;
    private Class<?> type;
    private String name;

    TableName(Class<?> typeClass, String name) {
        this.type = typeClass;
        this.name = name;
    }

    public Class<?> getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static String getTableName(Class<?> clazz) {
        Optional<TableName> optional = Arrays.stream(TableName.values()).filter(e -> e.type == clazz).findAny();
        return optional.map(TableName::getName).orElse(null);
    }
}
