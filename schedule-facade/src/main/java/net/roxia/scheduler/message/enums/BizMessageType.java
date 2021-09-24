package net.roxia.scheduler.message.enums;

/**
 * @ClassName BizMessageType
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/23 15:58
 **/
public enum BizMessageType {
    /**
     * 应答消息
     */
    RESPONSE,
    /**
     * 注册任务
     */
    REG_TASK,
    /**
     * 注销任务
     */
    LOGOFF_TASK,
    /**
     * 执行任务
     */
    EXECUTE_TASK;
}
