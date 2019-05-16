package com.meixiaoxi.scheduler.handler;

import com.meixiaoxi.scheduler.message.Header;
import com.meixiaoxi.scheduler.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class Decoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        // 获取协议的版本
        int version = in.readInt();
        // 获取消息长度
        int contentLength = in.readInt();
        // 获取key
        byte[] keyByte = new byte[36];
        in.readBytes(keyByte);
        String key = new String(keyByte);

        // 组装协议头
        Header header = new Header(version, contentLength, key);
        // 消息内容长度
        int length = in.readInt();
        byte[] content = new byte[length];
        // 读取消息内容
        in.readBytes(content);
        Message message = new Message(header, new String(content));
        out.add(message);
    }
}