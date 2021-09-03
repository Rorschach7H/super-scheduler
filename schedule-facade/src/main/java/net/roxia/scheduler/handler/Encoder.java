package net.roxia.scheduler.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.roxia.scheduler.message.Header;
import net.roxia.scheduler.message.Message;

import java.nio.charset.StandardCharsets;

@ChannelHandler.Sharable
public class Encoder extends MessageToByteEncoder<Message> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Message message, ByteBuf out) throws Exception {

        // 将Message转换成二进制数据
        Header header = message.getHeader();
        // 这里写入的顺序就是协议的顺序.
        // 写入Header信息
        out.writeInt(header.getVersion());
        String key = header.getKey();
        if (key == null) {
            throw new RuntimeException("message header key must be String!!!");
        }
        out.writeInt(header.getKey().getBytes().length);
        out.writeBytes(header.getKey().getBytes());
        writeString(out, header.getName());
        writeString(out, header.getAlias());
        writeString(out, header.getType());
        writeString(out, message.getBody());
    }

    private void writeString(ByteBuf out, String str) {
        if (str == null) {
            str = Message.default_null;
        }
        out.writeInt(str.getBytes(StandardCharsets.UTF_8).length);
        out.writeBytes(str.getBytes(StandardCharsets.UTF_8));
    }
}