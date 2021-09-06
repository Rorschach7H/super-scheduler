package net.roxia.scheduler.adapter.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * @ClassName OperateEnum
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/3 14:49
 **/
public enum OperateEnum {
    /**
     * 添加任务消息
     */
    REG_TASK("注册任务"),

    /**
     * 移除任务消息
     */
    LOGOFF_TASK("注销任务"),

    /**
     * 执行任务消息
     */
    EXECUTE_TASK("执行任务"),

    /**
     * 连接握手消息
     */
    REG_CLIENT("客户端注册"),
    /**
     * 断开挥手消息
     */
    LOGOFF_CLIENT("客户端注销");

    private String desc;

    OperateEnum(String desc) {
        this.desc = desc;
    }

    public static OperateEnum getByCode(String code) {
        return Arrays.stream(OperateEnum.values())
                .filter(e -> StringUtils.equalsAnyIgnoreCase(e.name(), code))
                .findAny().orElse(null);
    }

    public String getDesc() {
        return desc;
    }

    public static void main(String[] args) {
        System.out.println(getByCode("SAY_HELLO1"));
    }
}
