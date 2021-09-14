package net.roxia.scheduler.message;

/**
 * @ClassName MsgVersion
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/13 16:36
 **/
public enum MsgVersion {
    VERSION_1("1.0");

    private final String value;

    MsgVersion(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
