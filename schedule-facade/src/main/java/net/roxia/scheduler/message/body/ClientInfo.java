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
public class ClientInfo {

    /**
     * 注册客户端唯一标识
     */
    private String clientId;

    /**
     * 注册客户端名字
     */
    private String clientName;

    /**
     * 客户端IP
     */
    private String ip;

    /**
     * 准入密钥
     */
    private String accessKey;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }
}
