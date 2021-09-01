package net.roxia.scheduler.message;

import java.io.Serializable;

// 消息的主体
public class Message implements Serializable {

    public static String default_null = "--mxx--";

    private Header header;

    private String handler;

    public Message(Header header, String handler) {
        this.header = header;
        this.handler = handler;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    @Override
    public String toString() {
        return String.format("[version=%d,key=%s,name=%s,alias=%s,content=%s]",
                header.getVersion(),
                header.getKey(),
                header.getName(),
                header.getAlias(),
                handler);
    }
}