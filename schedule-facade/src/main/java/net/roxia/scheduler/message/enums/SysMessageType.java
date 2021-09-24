package net.roxia.scheduler.message.enums;

/**
 * @ClassName SysMessageType
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/23 15:57
 **/
public enum SysMessageType {

    CONNECT,
    /**
     * 首次连接握手
     */
    HANDSHAKE,
    /**
     * 服务器发送心跳PING消息
     */
    PING,
    /**
     * 客户端回应心跳PONG消息
     */
    PONG,
    /**
     * 服务端鉴权失败消息
     */
    AUTH_FAIL;

    public static SysMessageType value(String value) {
        try {
            return valueOf(value);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
