package net.roxia.scheduler.core.task.adapter;

import net.roxia.scheduler.adapter.OperateAdapter;
import net.roxia.scheduler.adapter.annotation.Operate;
import net.roxia.scheduler.common.utils.JsonUtil;
import net.roxia.scheduler.core.task.domain.RunExecutingTask;
import net.roxia.scheduler.message.body.TaskMsg;
import net.roxia.scheduler.message.protobuf.Message;
import net.roxia.scheduler.message.protobuf.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName TaskAddAdapter
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/3 16:32
 **/
@Operate(operate = MessageType.REG_TASK_VALUE)
public class TaskAddAdapter extends OperateAdapter {

    private final Logger log = LoggerFactory.getLogger(TaskAddAdapter.class);

    @Override
    public String handle(Message message) {
        String body = message.getBody();
        TaskMsg task = JsonUtil.string2Obj(body, TaskMsg.class);
        try {
//            if (cacheOperate.addTask(taskInfo)) {
//                dbOperate.addTask(taskInfo);
//            }
        } catch (Exception e) {
            log.error("添加任务出现异常！taskInfo=" + JsonUtil.obj2String(task), e);
            throw new RuntimeException(e);
        }
        return null;
    }
}
