package com.meixiaoxi.scheduler.core.network.client;

import com.meixiaoxi.scheduler.core.network.Decoder;
import com.meixiaoxi.scheduler.core.network.Encoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * Copyright: Copyright (c) 2018 meixiaoxi
 *
 * @ClassName: ClientInitializer
 * @Description:
 * @version: v1.0.0
 * @author: meixiaoxi
 * @date: 2019-04-26 17:07:05
 * Modification History:
 * Date          Author          Version          Description
 * -----------------------------------------------------------
 * 2019-04-26    meixiaoxi       v1.0.0           创建
 */
public class ClientInitializer extends ChannelInitializer<SocketChannel> {
    private static final Encoder ENCODER = new Encoder();

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {

        ChannelPipeline pipeline = channel.pipeline();

        // 添加编解码器, 由于ByteToMessageDecoder的子类无法使用@Sharable注解,
        // 这里必须给每个Handler都添加一个独立的Decoder.
        pipeline.addLast(ENCODER);
        pipeline.addLast(new Decoder());

        // and then business logic.
        pipeline.addLast(new ClientMessageHandler());

    }
}
