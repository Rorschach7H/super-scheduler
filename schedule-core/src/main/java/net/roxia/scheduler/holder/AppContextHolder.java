package net.roxia.scheduler.holder;

import net.roxia.scheduler.AppContext;
import net.roxia.scheduler.SchedulerConfig;

/**
 * @ClassName AppContextHolder
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/2 15:33
 **/
public class AppContextHolder {

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
