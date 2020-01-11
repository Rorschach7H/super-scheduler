package net.roxia.scheduler.core.task.domain;

import net.roxia.scheduler.common.utils.StringTools;
import net.roxia.scheduler.core.task.domain.annotation.Column;
import net.roxia.scheduler.core.task.domain.annotation.PrimaryKey;
import net.roxia.scheduler.core.task.domain.annotation.Table;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: AbstractEntity
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-14 14:00:10
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-14    meixiaoxi       v1.0.0           创建
 */
public abstract class AbstractEntity {

    public static String tableName(Class<?> clazz) {
        Table table = clazz.getAnnotation(Table.class);
        if (table != null) {
            return table.value();
        }
        return StringTools.camel2UnderLine(clazz.getSimpleName()).toString();
    }

    public String tableName() {
        return tableName(this.getClass());
    }

    public static String[] columns(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        String[] columns = new String[fields.length];
        Arrays.stream(fields)
                .map(AbstractEntity::columnName)
                .collect(Collectors.toList())
                .toArray(columns);
        return columns;
    }

    public String[] columns() {
        return columns(this.getClass());
    }

    public Object[] values() {
        Field[] fields = this.getClass().getDeclaredFields();
        return Arrays.stream(fields)
                .map(this::value)
                .toArray();
    }

    public Map<String, Object> keyValueMap(boolean ignoreNull) {
        Field[] fields = this.getClass().getDeclaredFields();
        Map<String, Object> map = new HashMap<>();
        for (Field field : fields) {
            if (ignoreNull && value(field) == null) {
                continue;
            }
            map.put(columnName(field), value(field));
        }
        return map;
    }

    public String primaryKey() {
        return primaryKey(this.getClass());
    }

    public static String primaryKey(Class<?> clazz) {
        Field field = primary(clazz);
        if (field == null) {
            return null;
        }
        Column column = field.getAnnotation(Column.class);
        if (column == null) {
            return StringTools.camel2UnderLine(field.getName()).toString();
        }
        return column.value();
    }

    /**
     * 得到主键的key-value值
     *
     * @return
     */
    public Object primaryValue() {
        Field field = primary(this.getClass());
        if (field != null) {
            return value(field);
        }
        return null;
    }

    private static Field primary(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(e -> e.getAnnotation(PrimaryKey.class) != null)
                .findAny().orElse(null);
    }

    /**
     * 得到属性值
     *
     * @param field
     * @return
     */
    private Object value(Field field) {
        try {
            field.setAccessible(true);
            return field.get(this);
        } catch (IllegalAccessException e) {
            throw new RuntimeException();
        }
    }

    /**
     * 得到属性对应的字段名
     *
     * @param field
     * @return
     */
    private static String columnName(Field field) {
        Column column = field.getAnnotation(Column.class);
        if (column != null) {
            return column.value();
        }
        return StringTools.camel2UnderLine(field.getName()).toString();
    }
}
