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
        String key = getString(in);

        String name = getString(in);
        String alias = getString(in);
        String handler = getString(in);
        Header header = new Header(version, key);
        header.setName(name);
        header.setAlias(alias);
        Message message = new Message(header, handler);
        out.add(message);
    }

    private String getString(ByteBuf in) {
        // 获取下一段消息内容长度
        int keyLength = in.readInt();
        // 获取下一段消息内容
        byte[] keyByte = new byte[keyLength];
        in.readBytes(keyByte);
        String str = new String(keyByte);
        if (Message.default_null.equals(str)) {
            return null;
        }
        return str;
    }
}