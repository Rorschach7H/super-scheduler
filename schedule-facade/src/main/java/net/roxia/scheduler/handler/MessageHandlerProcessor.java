package net.roxia.scheduler.handler;

import net.roxia.scheduler.adapter.OperateAdapter;
import net.roxia.scheduler.message.protobuf.Header;
import net.roxia.scheduler.message.protobuf.Message;
import net.roxia.scheduler.message.protobuf.MessageType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName MessageHandlerProcessor
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/3 15:10
 **/
public class MessageHandlerProcessor {
    private static final Logger log = LoggerFactory.getLogger(MessageHandlerProcessor.class);

    public static void assignmentMsg(Message message) {
        Header header = message.getHeader();
        MessageType type = header.getType();
        String result = OperateAdapter.getOperateAdapter(type).handle(message);

        Header responseHeader = Header.newBuilder(header)
                .setType(MessageType.MESSAGE_RESPONSE)
                .setTimestamp(System.currentTimeMillis())
                .build();
        Message response = Message.newBuilder(message)
                .clearHeader()
                .setBody(result)
                .setHeader(responseHeader)
                .build();
        log.info("client message deal result. \nrequest : {}, \nresponse: {}",
                messageToString(message), messageToString(response));
    }

    private static String messageToString(Message message) {
        Header header = message.getHeader();
        return String.format("\n\theader：[\n\t\trequestId:%s \n\t\tversion:%s \n\t\taccessKey:%s \n\t\tgroup:%s " +
                        "\n\t\tmachineId:%s \n\t\tmessageType:%s \n\t\ttimestamp:%s \n\t] \n\tbody: [\n\t\t%s\n\t]",
                header.getRequestId(), header.getVersion(), header.getAccessKey(),
                header.getGroup(), header.getMachineId(), header.getType().name(),
                header.getTimestamp(), message.getBody());
    }
}
