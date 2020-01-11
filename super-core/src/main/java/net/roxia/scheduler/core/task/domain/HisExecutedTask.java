package net.roxia.scheduler.core.task.domain;


import net.roxia.scheduler.common.utils.JsonUtil;

import java.math.BigDecimal;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: ExecutedTask
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-06 17:15:01
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-06    meixiaoxi       v1.0.0           创建
 */
public class HisExecutedTask {
    /**
     * 任务ID
     */
    private Long id = 0L;

    /**
     * 要执行的对象ID
     */
    private Long objectId;

    /**
     * 任务名
     */
    private String taskName;

    /**
     * 任务组名
     */
    private String groupKey;

    /**
     * 定时任务cron表达式
     */
    private String cron;

    /**
     * 定时任务执行周期
     */
    private Integer period;

    /**
     * 定时任务相关时间参数的单位(0|秒,1|分,2|时)
     */
    private Byte timeUnit;

    /**
     * 定时任务开始执行的延时
     */
    private Integer initialDelay;

    /**
     * 接入客户端key
     */
    private String accessKey;

    /**
     * 执行时间
     */
    private String executeTime;

    /**
     * 同一时间下（精确到秒）任务序列
     */
    private BigDecimal executeQueue;

    /**
     * 执行任务已失败次数
     */
    private Integer failures;
    /**
     * 最大失败次数
     */
    private Integer maxFailures;

    /**
     * 历史任务结果描述
     */
    private String description;

    /**
     * 任务状态
     */
    private Byte executeState;

    /**
     * 创建时间
     */
    private String createTime;

    private Byte state;

    @Override
    public String toString() {
        return JsonUtil.toJsonString(this);
    }
}
