package com.meixiaoxi.scheduler.store.jdbc;

import com.meixiaoxi.scheduler.core.model.Task;
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
public interface PersistenceHandler {

    public boolean add(Task task);

    public boolean add(List<Task> taskList);

    public boolean update(Task task);

    public boolean update(List<Task> taskList);

    public int delete(Long taskId);

    Task get(Long taskId);

    List<Task> get(String taskGroup, String executeDate, long objectId);

    List<Task> getUnReadyTask();
}
