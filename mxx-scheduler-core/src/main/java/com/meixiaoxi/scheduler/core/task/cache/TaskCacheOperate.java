package com.meixiaoxi.scheduler.core.task.cache;

import com.meixiaoxi.scheduler.SchedulerConfig;
import com.meixiaoxi.scheduler.constant.ConfigSpiKeys;
import com.meixiaoxi.scheduler.core.task.domain.RunExecutingTask;
import com.meixiaoxi.scheduler.spi.SPI;

import java.util.List;

/**
 * @Title 任务处理引擎接口
 * @Description
 * @Team 新金融业务研发团队
 * @Author HUANGJUNWEI452
 * @Date 2018/8/27 17:35
 * @Version V1.0
 */
@SPI(defaultName = "redis", dynamicKey = ConfigSpiKeys.CACHE_SPI)
public interface TaskCacheOperate {

    TaskCacheOperate loadConfig(SchedulerConfig config);

    /**
     * 添加任务
     *
     * @param taskInfo
     * @return
     */
    boolean addTask(RunExecutingTask taskInfo);

    /**
     * 删除任务
     *
     * @param objectId
     * @return
     */
    boolean removeTask(Long objectId, String groupKey);

    /**
     * 弹出(获取并删除)任务
     *
     * @param groupKey
     */
    List<RunExecutingTask> popExecuteTask(String groupKey);
}
