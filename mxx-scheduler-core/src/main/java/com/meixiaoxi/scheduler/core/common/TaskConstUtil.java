package com.meixiaoxi.scheduler.core.common;

import java.util.regex.Pattern;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: TaskConstUtil
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-03-01 14:10:34
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-03-01    meixiaoxi       v1.0.0           创建
 */
public class TaskConstUtil {

    public static final String TIME_YYYYMMDDHHMMSS = "yyyy-MM-dd hh:mm:ss";
    public static final String TIME_YYYYMM = "yyyy-MM-dd";
    Pattern COMMA_SPLIT_PATTERN = Pattern.compile("\\s*[,]+\\s*");
    public static final String SYNC_TASK_GROUP_LIST_KEY = "sync_task_group_list_key";
}
