package net.roxia.scheduler.holder;

import net.roxia.scheduler.AppContext;
import net.roxia.scheduler.SchedulerConfig;
import net.roxia.scheduler.persistence.PersistenceContext;
import net.roxia.scheduler.store.SqlTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName AppContextHolder
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/2 15:33
 **/
public class AppContextHolder {

    private static AppContext appContext;

    /**
     * 数据库上下文
     */
    private static PersistenceContext persistenceContext;

    public static void setAppContext(AppContext appContext) {
        AppContextHolder.appContext = appContext;
    }

    public static void setPersistenceContext(PersistenceContext persistenceContext) {
        AppContextHolder.persistenceContext = persistenceContext;
    }

    public static SchedulerConfig getGlobalConfig() {
        return appContext.getConfig();
    }

    public static <T> T getClass(Class<T> clazz) {
        return appContext.getBean(clazz);
    }

    public static SqlTemplate getSqlTemplate() {
        return AppContextHolder.persistenceContext.getSqlTemplate();
    }
}
