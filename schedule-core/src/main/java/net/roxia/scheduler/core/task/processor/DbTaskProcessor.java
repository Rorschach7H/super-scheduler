package net.roxia.scheduler.core.task.processor;

import net.roxia.scheduler.core.task.domain.RunExecutingTask;
import net.roxia.scheduler.persistence.mapper.RunTaskMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @ClassName DbTaskOperate
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/2 14:39
 **/
public class DbTaskProcessor implements TaskProcessor {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private RunTaskMapper taskMapper;

    private static TaskProcessor taskOperate;

    private DbTaskProcessor() {
        taskMapper = new RunTaskMapper();
    }

    public synchronized static TaskProcessor getTaskOperate() {
        if (taskOperate != null) {
            return taskOperate;
        }
        synchronized (TaskProcessor.class) {
            if (taskOperate != null) {
                return taskOperate;
            }
            taskOperate = new TaskProcessor().loadConfig();
            return taskOperate;
        }
    }

    @Override
    public TaskProcessor loadConfig() {
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
