package net.roxia.scheduler.core.processor;

import net.roxia.scheduler.adapter.annotation.Operate;
import net.roxia.scheduler.adapter.enums.OperateEnum;
import net.roxia.scheduler.common.utils.JsonUtil;
import net.roxia.scheduler.core.task.domain.RunExecutingTask;
import net.roxia.scheduler.message.Message;

/**
 * @ClassName TaskAddAdapter
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/3 16:32
 **/
@Operate(operate = OperateEnum.REG_TASK)
public class TaskAddAdapter extends AbstractTaskAdapter {
    @Override
    public String handle(Message message) {
        String body = message.getBody();
        RunExecutingTask taskInfo;
        try {
            taskInfo = JsonUtil.string2Obj(body, RunExecutingTask.class);
        } catch (Exception ex) {
            log.error("pare task body error！taskInfo={}", body, ex);
            throw new RuntimeException(ex);
        }
        try {
            if (cacheOperate.addTask(taskInfo)) {
                dbOperate.addTask(taskInfo);
            }
        } catch (Exception e) {
            log.error("添加任务出现异常！taskInfo=" + JsonUtil.obj2String(taskInfo), e);
            throw new RuntimeException(e);
        }
        return null;
    }
}
