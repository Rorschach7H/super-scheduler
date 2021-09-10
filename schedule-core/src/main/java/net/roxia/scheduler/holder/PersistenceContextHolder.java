package net.roxia.scheduler.holder;

import net.roxia.scheduler.persistence.PersistenceContext;
import net.roxia.scheduler.store.SqlTemplate;

/**
 * @ClassName AppContextHolder
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/2 15:33
 **/
public class PersistenceContextHolder {
    /**
     * 数据库上下文
     */
    private static PersistenceContext persistenceContext;

    public static void setPersistenceContext(PersistenceContext persistenceContext) {
        PersistenceContextHolder.persistenceContext = persistenceContext;
    }

    public static SqlTemplate getSqlTemplate() {
        return PersistenceContextHolder.persistenceContext.getSqlTemplate();
    }
}
