package com.meixiaoxi.scheduler.result;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: Action
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-04-25 15:03:38
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-04-25    meixiaoxi       v1.0.0           创建
 */
public enum Action {
    EXECUTE_SUCCESS,    // 执行成功,这种情况 直接反馈客户端
    EXECUTE_FAILED,     // 执行失败,这种情况,直接反馈给客户端,不重新执行
    EXECUTE_LATER,       // 稍后重新执行,这种情况, 不反馈客户端,稍后重新执行,不过有最大重试次数
    EXECUTE_EXCEPTION   // 执行异常, 这中情况也会重试
}
