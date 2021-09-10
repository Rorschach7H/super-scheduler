package net.roxia.scheduler.task.client;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName Client
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/9 14:42
 **/
@Getter
@Setter
public class Client {
    /**
     * 注册客户端唯一标识
     */
    private String machineId;

    /**
     * 注册客户端名字
     */
    private String group;

    /**
     * 客户端IP
     */
    private String ip;

    /**
     * 准入密钥
     */
    private String accessKey;
}
