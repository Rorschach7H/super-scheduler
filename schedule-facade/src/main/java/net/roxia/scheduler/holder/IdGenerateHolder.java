package net.roxia.scheduler.holder;

import net.roxia.scheduler.message.enums.BizMessageType;
import net.roxia.scheduler.message.enums.SysMessageType;

/**
 * @ClassName IdGenerateHolder
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/22 17:27
 **/
public class IdGenerateHolder {

    /**
     * 系统/业务_消息类型_ID
     * 例 BIZ_CONNECT_402342897234234824
     */
    private final static String REQUEST_ID_TEMPLATE = "%s_%s_%s";

    public static String generateRequestId(SysMessageType messageType, String id) {
        return String.format(REQUEST_ID_TEMPLATE, "SYS", messageType.name(), id);
    }

    public static String generateRequestId(BizMessageType messageType, String id) {
        return String.format(REQUEST_ID_TEMPLATE, "BIZ", messageType.name(), id);
    }
}
