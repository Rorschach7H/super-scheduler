package net.roxia.scheduler.message;

import lombok.*;

import java.io.Serializable;

/**
 * 消息的头部
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Header implements Serializable {

    public static final int CURRENT_VERSION = 1;

    /**
     * 协议版本
     */
    private int version;
    /**
     * 连接KEY
     */
    private String accessKey;
    /**
     * 客户端组
     */
    private String group;

    /**
     * 消息类型
     *
     * @see net.roxia.scheduler.adapter.enums.OperateEnum
     */
    private String type;
}