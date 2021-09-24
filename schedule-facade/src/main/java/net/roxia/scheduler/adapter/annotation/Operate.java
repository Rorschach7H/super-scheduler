package net.roxia.scheduler.adapter.annotation;

import net.roxia.scheduler.message.enums.BizMessageType;

import java.lang.annotation.*;

/**
 * @ClassName OperateAdapter
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/3 15:50
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Operate {
    /**
     * 消息类型
     */
    BizMessageType operate();
}
