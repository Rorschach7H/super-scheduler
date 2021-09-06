package net.roxia.scheduler.client;

import com.google.common.collect.Maps;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import net.roxia.scheduler.adapter.enums.OperateEnum;
import net.roxia.scheduler.common.utils.JsonUtil;
import net.roxia.scheduler.message.Header;
import net.roxia.scheduler.message.Message;
import net.roxia.scheduler.message.body.ClientInfo;

import java.util.Map;
import java.util.UUID;

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
        Client client = new Client();
        client.start();
    }
}
