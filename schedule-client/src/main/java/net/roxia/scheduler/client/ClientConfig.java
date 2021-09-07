package net.roxia.scheduler.client;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName ClientConfig
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/7 11:02
 **/
@Getter
@Setter
public class ClientConfig {
    private String host;
    private int port;
    private String group;
    private String accessKey;
}
