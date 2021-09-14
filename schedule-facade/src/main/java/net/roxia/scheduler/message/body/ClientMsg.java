package net.roxia.scheduler.message.body;

import lombok.*;

/**
 * @ClassName ClientInfo
 * @Description 客户端注册信息
 * @Author huangjunwei01
 * @Date 2021/9/6 16:04
 **/
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class ClientMsg {

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

    /**
     * 激活状态
     * 0-未激活，1-已激活
     */
    private Boolean active;
}
