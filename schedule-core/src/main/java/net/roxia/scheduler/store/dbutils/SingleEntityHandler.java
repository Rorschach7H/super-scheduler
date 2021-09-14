package net.roxia.scheduler.store.dbutils;

import net.roxia.scheduler.common.utils.StringTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName EntityHandler
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/13 17:17
 **/
public class SingleEntityHandler<T> implements ResultSetHandler<T> {

    private static final Logger log = LoggerFactory.getLogger(SingleEntityHandler.class);

    private final Class<T> entityClass;
    private final Field[] fields;

    public SingleEntityHandler(Class<T> clazz) {
        entityClass = clazz;
        this.fields = entityClass.getDeclaredFields();
    }

    @Override
    public T handle(ResultSet rs) throws SQLException {
        try {
            if (rs.next()) {
                T t = entityClass.newInstance();
                for (Field field : fields) {
                    String columnName = StringTools.camel2UnderLine(field.getName());
                    field.setAccessible(true);
                    Object value = typeConvert(rs.getObject(columnName));
                    field.set(t, value);
                }
                return t;
            }
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("single entity convert error!", e);
        }
        return null;
    }

    public Object typeConvert(Object value) {
        if (value instanceof BigInteger) {
            return ((BigInteger) value).longValue();
        }
        return value;
    }
}
