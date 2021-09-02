package net.roxia.scheduler.core.task;

import net.roxia.scheduler.AppContext;
import net.roxia.scheduler.core.task.domain.RunExecutingTask;
import net.roxia.scheduler.core.task.mysql.MysqlTaskMapper;
import net.roxia.scheduler.core.task.mysql.TaskMapper;
import net.roxia.scheduler.holder.AppContextHolder;

import java.util.List;

/**
 * @ClassName DbTaskOperate
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/2 14:39
 **/
public class DbTaskOperate implements TaskOperate{

    private TaskMapper taskMapper;

    @Override
    public TaskOperate loadConfig() {
        taskMapper = new MysqlTaskMapper(AppContextHolder.getGlobalConfig());
        return null;
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
