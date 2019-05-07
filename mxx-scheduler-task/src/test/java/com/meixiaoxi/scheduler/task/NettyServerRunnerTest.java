package com.meixiaoxi.scheduler.task;

import com.meixiaoxi.scheduler.task.runner.NettyServerRunner;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: NettyServerRunnerTest
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-05-06 17:38:10
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-05-06    meixiaoxi       v1.0.0           创建
 */
public class NettyServerRunnerTest {

    public static void main(String[] args) {
        NettyServerRunner runner = new NettyServerRunner(null);
        runner.start();
    }
}
