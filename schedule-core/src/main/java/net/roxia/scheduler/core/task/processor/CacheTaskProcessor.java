package net.roxia.scheduler.core.task.processor;

import net.roxia.scheduler.common.utils.DateUtil;
import net.roxia.scheduler.common.utils.JsonUtil;
import net.roxia.scheduler.core.handler.TaskExecuteHandler;
import net.roxia.scheduler.core.task.domain.ExecuteState;
import net.roxia.scheduler.core.task.domain.RunExecutingTask;
import net.roxia.scheduler.redis.RedissonFactory;
import org.apache.commons.collections4.CollectionUtils;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.protocol.ScoredEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Title 任务运行处理器
 * @Description
 * @Team 新金融业务研发团队
 * @Author HUANGJUNWEI452
 * @Date 2018/7/9 11:24
 * @Version V1.0
 */
public class CacheTaskProcessor implements TaskProcessor {

    private final Logger log = LoggerFactory.getLogger(CacheTaskProcessor.class);

    private final static String postfix = "roxia-scheduler:";

    private RedissonClient redissonClient;

    private CacheTaskProcessor() {
        try {
            redissonClient = RedissonFactory.getRedissonClient();
        } catch (Exception e) {
            log.warn("init redis connect failed! ", e);
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
            List<RunExecutingTask> taskInfoList = popExecuteTask(taskGroup);
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
                //cacheOperate.addTask(task);
            }
        } catch (Exception e) {
            log.error("将未就绪任务添加到执行队列出现异常", e);
        }
    }

    private List<RunExecutingTask> popExecuteTask(String taskGroup) {
        long timestamp = new Date().getTime() / 1000;
        List<RunExecutingTask> taskInfoList = new ArrayList<>();
        while (true) {
            try {
                Set<RunExecutingTask> taskInfoSet = getAndRemoveTaskByScore(taskGroup, timestamp);
                if (CollectionUtils.isEmpty(taskInfoSet)) {
                    break;
                }
                log.info("从任务组[{}],得到score<=[{}]的任务: {}",
                        taskGroup,
                        timestamp,
                        JsonUtil.obj2String(taskInfoSet));
                taskInfoList.addAll(taskInfoSet);
            } catch (Exception e) {
                log.warn("获取任务出现异常:", e);
                break;
            }
        }

        return taskInfoList;
    }

    /**
     * 获取并移除任务
     *
     * @param taskGroup
     * @param timestamp
     * @return
     */
    private Set<RunExecutingTask> getAndRemoveTaskByScore(String taskGroup, long timestamp) {
        RScoredSortedSet<String> sortedSet = redissonClient.getScoredSortedSet(taskGroup + postfix);
        Collection<ScoredEntry<String>> taskInfoList =
                sortedSet.entryRange(0, false, timestamp, true);
        if (CollectionUtils.isNotEmpty(taskInfoList)) {
            //如果获取到最要执行的任务，那么进行从任务组出移除
            sortedSet.removeRangeByScore(0, false, timestamp, true);
            return taskInfoList.stream().map(entry -> JsonUtil.string2Obj(entry.getValue(), RunExecutingTask.class)).collect(Collectors.toSet());
        }
        return null;
    }
}
