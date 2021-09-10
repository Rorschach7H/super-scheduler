package net.roxia.scheduler.task;

import net.roxia.scheduler.AppContext;
import net.roxia.scheduler.SchedulerConfig;
import net.roxia.scheduler.holder.PersistenceContextHolder;

/**
 * @ClassName AppContextHolder
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/2 15:33
 **/
public class AppContextHolder extends PersistenceContextHolder {

    private static AppContext appContext;

    public static void setAppContext(AppContext appContext) {
        AppContextHolder.appContext = appContext;
    }

    public static SchedulerConfig getGlobalConfig() {
        return appContext.getConfig();
    }

    public static <T> T getClass(Class<T> clazz) {
        return appContext.getBean(clazz);
    }
}
