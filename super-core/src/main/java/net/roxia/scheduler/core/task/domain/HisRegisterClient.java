package net.roxia.scheduler.core.task.domain;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: HisRedisterClient
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-06 17:16:42
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-06    meixiaoxi       v1.0.0           创建
 */
public class HisRegisterClient {
    private Long id;
    private String accessKey;
    private String ip;
    private String createTime;
    private String accessStart;
    private String accessEnd;
    private Byte state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getAccessStart() {
        return accessStart;
    }

    public void setAccessStart(String accessStart) {
        this.accessStart = accessStart;
    }

    public String getAccessEnd() {
        return accessEnd;
    }

    public void setAccessEnd(String accessEnd) {
        this.accessEnd = accessEnd;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }
}
