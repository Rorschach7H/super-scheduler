package net.roxia.scheduler.core.task.mysql;

import net.roxia.scheduler.core.task.domain.RunExecutingTask;
import net.roxia.scheduler.core.task.domain.TaskQuery;

import java.util.List;

/**
 * @Title 任务数据库操作接口
 * @Description
 * @Team 新金融业务研发团队
 * @Author HUANGJUNWEI452
 * @Date 2018/8/28 10:05
 * @Version V1.0
 */
public interface TaskMapper {

    boolean insertSelective(RunExecutingTask task);

    boolean insert(RunExecutingTask task);

    boolean insertBatch(List<RunExecutingTask> task);

    boolean update(RunExecutingTask task);

    boolean updateBatch(List<RunExecutingTask> tasks);

    boolean delete(Long taskId);

    RunExecutingTask select(Long id);

    List<RunExecutingTask> select(TaskQuery query);
}
