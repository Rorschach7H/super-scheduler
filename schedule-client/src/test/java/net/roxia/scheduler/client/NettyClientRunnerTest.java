package net.roxia.scheduler.client;

import net.roxia.scheduler.common.utils.StringTools;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: NettyClientRunnerTest
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-06 17:40:04
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-06    meixiaoxi       v1.0.0           创建
 */
public class NettyClientRunnerTest {
    public static void main(String[] args) throws Exception {
        ClientConfig config = new ClientConfig();
        config.setHost("localhost");
        config.setPort(9088);
        config.setGroup("test-schedule-client");
        config.setAccessKey("6c3705c9f0df440097ec98563c404358");
        Client.init(config);
        Client.start();
    }
}
