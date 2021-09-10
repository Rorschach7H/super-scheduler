package net.roxia.scheduler.core.task.processor;

import net.roxia.scheduler.TaskException;
import net.roxia.scheduler.common.utils.JsonUtil;
import net.roxia.scheduler.core.handler.TaskExecuteHandler;
import net.roxia.scheduler.core.task.domain.ExecuteState;
import net.roxia.scheduler.core.task.domain.RunExecutingTask;
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
public class TaskProcessorEngine implements TaskProcessor {

    private static final Logger log = LoggerFactory.getLogger(TaskProcessorEngine.class);

    private final TaskProcessor cacheTaskProcessor;
    private final TaskProcessor dbTaskProcessor;

    public TaskProcessorEngine() {
        cacheTaskProcessor = CacheTaskProcessor.getCacheTaskProcessor();
        dbTaskProcessor = DbTaskProcessor.getCacheTaskProcessor();
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
        return null;
    }

    @Override
    public void addUnReadyTaskToQueue(List<RunExecutingTask> tasks) {
        log.info("将未就绪任务添加到执行队列tasks={}", JsonUtil.obj2String(tasks));
        try {
            for (RunExecutingTask task : tasks) {
                task.setExecuteState(ExecuteState.WAIT_EXECUTE.getCode());
                if (cacheTaskProcessor.addTask(task)) {
                    dbTaskProcessor.addTask(task);
                }
            }
        } catch (Exception e) {
            log.error("将未就绪任务添加到执行队列出现异常", e);
        }
    }
}
