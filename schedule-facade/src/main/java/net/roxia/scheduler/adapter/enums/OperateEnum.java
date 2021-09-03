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
    ADD_TASK("添加任务"),

    /**
     * 移除任务消息
     */
    REMOVE_TASK("移除任务"),

    /**
     * 执行任务消息
     */
    EXECUTE_TASK("执行任务"),

    /**
     * 连接握手消息
     */
    SAY_HELLO("连接握手"),
    /**
     * 断开挥手消息
     */
    SAY_BYE("断开挥手");

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
