package net.roxia.scheduler.core.task.processor;

import net.roxia.scheduler.TaskException;
import net.roxia.scheduler.common.utils.DateUtil;
import net.roxia.scheduler.common.utils.JsonUtil;
import net.roxia.scheduler.core.handler.TaskExecuteHandler;
import net.roxia.scheduler.core.task.CacheTaskOperate;
import net.roxia.scheduler.core.task.DbTaskOperate;
import net.roxia.scheduler.core.task.TaskOperate;
import net.roxia.scheduler.core.task.domain.ExecuteState;
import net.roxia.scheduler.core.task.domain.RunExecutingTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * @Title 任务运行处理器
 * @Description
 * @Team 新金融业务研发团队
 * @Author HUANGJUNWEI452
 * @Date 2018/7/9 11:24
 * @Version V1.0
 */
public class TaskProcessorEngine implements TaskProcessor {

    private static final Logger log = LoggerFactory.getLogger(TaskProcessorEngine.class);

    public TaskProcessorEngine() {
        cacheOperate = CacheTaskOperate.getTaskOperate();
        dbOperate = DbTaskOperate.getTaskOperate();
        if (cacheOperate == null && dbOperate == null) {
            throw new TaskException("cache operate and db operate init failed please check config!");
        }
    }

    @Override
    public boolean addTask(RunExecutingTask taskInfo) {

        return false;
    }

    /**
     * 执行任务
     *
     * @param taskGroup
     * @param handler
     */
    @Override
    public List<RunExecutingTask> executeTask(String taskGroup, TaskExecuteHandler handler) {
        try {
            List<RunExecutingTask> taskInfoList = cacheOperate.popExecuteTask(taskGroup);
            for (RunExecutingTask taskInfo : taskInfoList) {
                //执行失败
                log.warn("任务执行失败！taskInfo={}", JsonUtil.obj2String(taskInfo));
                //失败次数加1
                taskInfo.setFailures(taskInfo.getFailures() + 1);
                if (taskInfo.getFailures().intValue() == taskInfo.getMaxFailures().intValue()) {
                    log.warn("该任务失败次数已经达到了最大失败次数(3次)！无法继续尝试运行，请检查任务对应的业务逻辑是否正确，taskInfo={}", JsonUtil.obj2String(taskInfo));
                    taskInfo.setExecuteState((byte) ExecuteState.FAIL.getCode());
                    continue;
                }
                //下一次执行时间改为当前时间的10秒后
                taskInfo.setExecuteTime(DateUtil.dateToString(DateUtil.changeDate(new Date(),
                        DateUtil.TimeUnit.second, 10), DateUtil.DEFAULT_TIME));
                //重新放回到任务等待队列中
                addTask(taskInfo);
                log.info("重新添加该任务到[{}]任务组, taskInfo={}", taskInfo.getTaskName(), taskInfo);
            }
//            if (CollectionUtils.isNotEmpty(taskInfoList)) {
//                cacheOperate.updateBatch(taskInfoList);
//            }
            return taskInfoList;
        } catch (Exception e) {
            log.error("执行任务出现异常！", e);
            return null;
        }
    }

    @Override
    public void addUnReadyTaskToQueue(List<RunExecutingTask> tasks) {
        log.info("将未就绪任务添加到执行队列tasks={}", JsonUtil.obj2String(tasks));
        try {
            for (RunExecutingTask task : tasks) {
                task.setExecuteState(ExecuteState.WAIT_EXECUTE.getCode());
                if (cacheOperate.addTask(task)) {
                    dbOperate.addTask(task);
                }
            }
        } catch (Exception e) {
            log.error("将未就绪任务添加到执行队列出现异常", e);
        }
    }
}
