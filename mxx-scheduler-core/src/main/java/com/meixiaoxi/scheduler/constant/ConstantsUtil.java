package com.meixiaoxi.scheduler.constant;

import java.util.regex.Pattern;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: Constants
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-04-28 17:45:04
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-04-28    meixiaoxi       v1.0.0           创建
 */
public interface ConstantsUtil {
    public Pattern COMMA_SPLIT_PATTERN = Pattern.compile("\\s*[,]+\\s*");
    public String LINE_SEPARATOR = System.getProperty("line.separator");
    public String CHARSET = "UTF-8";
}
