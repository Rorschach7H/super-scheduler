package com.meixiaoxi.scheduler.core.processor;

import com.alibaba.fastjson.JSON;
import com.meixiaoxi.scheduler.SchedulerConfig;
import com.meixiaoxi.scheduler.core.handler.TaskExecuteHandler;
import com.meixiaoxi.scheduler.core.task.TaskOperate;
import com.meixiaoxi.scheduler.core.task.domain.ExecuteState;
import com.meixiaoxi.scheduler.core.task.domain.RunExecutingTask;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @Title 任务运行处理器
 * @Description
 * @Team 新金融业务研发团队
 * @Author HUANGJUNWEI452
 * @Date 2018/7/9 11:24
 * @Version V1.0
 */
public class PersistenceTaskProcessor extends BaseTaskProcessor {

    private static Logger log = LoggerFactory.getLogger(PersistenceTaskProcessor.class);

    private TaskOperate taskOperate;

    public PersistenceTaskProcessor(SchedulerConfig config) {
        super(config);
        this.taskOperate = taskOperate;
    }

    public boolean addTask(RunExecutingTask taskInfo) {
        try {
            if (super.addTask(taskInfo)) {
                return taskOperate.insert(taskInfo);
            }
        } catch (Exception e) {
            log.error("添加任务出现异常！taskInfo=" + JSON.toJSONString(taskInfo), e);
            return false;
        }

        return false;
    }

    /**
     * 执行任务
     *
     * @param taskGroup
     * @param handler
     */
    public List<RunExecutingTask> executeTask(String taskGroup, TaskExecuteHandler handler) {
        try {
            List<RunExecutingTask> taskInfoList = super.executeTask(taskGroup, handler);
            if (CollectionUtils.isNotEmpty(taskInfoList)) {
                taskOperate.updateBatch(taskInfoList);
            }
            return taskInfoList;
        } catch (Exception e) {
            log.error("执行任务出现异常！", e);
            return null;
        }
    }

    @Override
    public void addUnReadyTaskToQueue(List<RunExecutingTask> tasks) {
        log.info("将未就绪任务添加到执行队列tasks={}", JSON.toJSONString(tasks));
        try {
            for (RunExecutingTask task : tasks) {
                task.setExecuteState(ExecuteState.WAIT_EXECUTE.getCode());
                if (taskOperate.update(task)) {
                    super.addTask(task);
                }
            }
        } catch (Exception e) {
            log.error("将未就绪任务添加到执行队列出现异常", e);
        }
    }
}
