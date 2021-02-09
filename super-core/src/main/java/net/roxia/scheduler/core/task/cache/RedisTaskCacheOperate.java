package net.roxia.scheduler.core.task.cache;

import net.roxia.scheduler.common.utils.DateUtil;
import net.roxia.scheduler.common.utils.JsonUtil;
import net.roxia.scheduler.core.task.domain.RunExecutingTask;
import net.roxia.scheduler.redis.RedissonFactory;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
public class RedisTaskCacheOperate implements TaskCacheOperate {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private static String postfix = "_nmslwsnd";

    private RedissonClient redissonClient;

    public TaskCacheOperate loadConfig() {
        redissonClient = RedissonFactory.getRedissonClient();
        return this;
    }

    @Override
    public boolean addTask(RunExecutingTask taskInfo) {
        try {
            if (check(taskInfo)) {
                long timestamp =
                        DateUtil.dateToTimestramp(taskInfo.getExecuteTime(), DateUtil.DEFAULT_TIME);
                RScoredSortedSet<String> scoredSortedSet =
                        redissonClient.getScoredSortedSet(taskInfo.getGroupKey());
                log.info("添加任务到[{}]任务组, score={}", taskInfo.getGroupKey(), timestamp);
                scoredSortedSet.addAsync(timestamp, taskInfo.toString());
            } else {
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error("添加任务出现异常！taskInfo=" + JsonUtil.obj2String(taskInfo), e);
            return false;
        }
    }

    @Override
    public boolean removeTask(Long objectId, String groupKey) {
        return false;
    }

    /**
     * 执行任务
     *
     * @param taskGroup
     */
    @Override
    public List<RunExecutingTask> popExecuteTask(String taskGroup) {
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

    private boolean check(RunExecutingTask taskInfo) {
        if (StringUtils.isBlank(taskInfo.getExecuteTime())) {
            log.warn("taskInfo无执行时间！");
            return false;
        }
        if (StringUtils.isBlank(taskInfo.getGroupKey())) {
            log.warn("taskInfo无任务组名！");
            return false;
        }
        return true;
    }
}
