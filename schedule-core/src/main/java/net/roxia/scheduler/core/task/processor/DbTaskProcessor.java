package net.roxia.scheduler.core.task.processor;

import net.roxia.scheduler.core.handler.TaskExecuteHandler;
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

    private final Logger log = LoggerFactory.getLogger(DbTaskProcessor.class);

    private RunTaskMapper taskMapper;

    private static TaskProcessor taskProcessor;

    private DbTaskProcessor() {
        taskMapper = new RunTaskMapper();
    }

    public static synchronized TaskProcessor getCacheTaskProcessor() {
        if (taskProcessor != null) {
            return taskProcessor;
        }
        synchronized (CacheTaskProcessor.class) {
            if (taskProcessor != null) {
                return taskProcessor;
            }
            taskProcessor = new DbTaskProcessor();
            return taskProcessor;
        }
    }

    @Override
    public boolean addTask(RunExecutingTask taskInfo) {
        return false;
    }

    @Override
    public List<RunExecutingTask> executeTask(String taskGroup, TaskExecuteHandler handler) {
        return null;
    }

    @Override
    public void addUnReadyTaskToQueue(List<RunExecutingTask> tasks) {

    }
}
