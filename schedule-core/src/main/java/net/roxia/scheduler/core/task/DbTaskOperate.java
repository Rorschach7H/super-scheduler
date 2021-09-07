package net.roxia.scheduler.core.task;

import net.roxia.scheduler.core.task.domain.RunExecutingTask;
import net.roxia.scheduler.core.task.mysql.MysqlTaskMapper;
import net.roxia.scheduler.core.task.mysql.TaskMapper;
import net.roxia.scheduler.holder.AppContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @ClassName DbTaskOperate
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/2 14:39
 **/
public class DbTaskOperate implements TaskOperate {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private TaskMapper taskMapper;

    private static TaskOperate taskOperate;

    private DbTaskOperate() {
    }

    public synchronized static TaskOperate getTaskOperate() {
        if (taskOperate != null) {
            return taskOperate;
        }
        synchronized (DbTaskOperate.class) {
            if (taskOperate != null) {
                return taskOperate;
            }
            taskOperate = new DbTaskOperate().loadConfig();
            return taskOperate;
        }
    }

    @Override
    public TaskOperate loadConfig() {
        try {
            taskMapper = new MysqlTaskMapper();
            return this;
        } catch (Exception ex) {
            log.warn("init db connect failed! {}", ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean addTask(RunExecutingTask taskInfo) {
        return false;
    }

    @Override
    public boolean removeTask(Long objectId, String groupKey) {
        return false;
    }

    @Override
    public List<RunExecutingTask> popExecuteTask(String groupKey) {
        return null;
    }
}
