package com.meixiaoxi.scheduler.core.task;

import com.meixiaoxi.scheduler.core.task.domain.TaskPo;
import com.meixiaoxi.scheduler.core.task.domain.TaskQuery;
import com.meixiaoxi.scheduler.spi.SPI;

import java.util.List;

/**
 * @Title 任务数据库操作接口
 * @Description
 * @Team 新金融业务研发团队
 * @Author HUANGJUNWEI452
 * @Date 2018/8/28 10:05
 * @Version V1.0
 */
@SPI(defaultValue = "mysql")
public interface TaskOperate {
    boolean insert(TaskPo task);

    boolean insertBatch(List<TaskPo> task);

    boolean update(TaskPo task);

    boolean updateBatch(List<TaskPo> tasks);

    int delete(Long taskId);

    TaskPo select(Long id);

    List<TaskPo> select(TaskQuery query);
}
