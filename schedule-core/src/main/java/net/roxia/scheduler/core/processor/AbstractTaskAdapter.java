package net.roxia.scheduler.core.processor;

import net.roxia.scheduler.TaskException;
import net.roxia.scheduler.adapter.OperateAdapter;
import net.roxia.scheduler.core.task.CacheTaskOperate;
import net.roxia.scheduler.core.task.DbTaskOperate;
import net.roxia.scheduler.core.task.TaskOperate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName AbstractTaskAdapter
 * @Description TODO
 * @Author huangjunwei01
 * @Date 2021/9/3 17:02
 **/
public abstract class AbstractTaskAdapter extends OperateAdapter {

    protected static final Logger log = LoggerFactory.getLogger(AbstractTaskAdapter.class);

    protected final TaskOperate cacheOperate;
    protected final TaskOperate dbOperate;

    public AbstractTaskAdapter() {
        cacheOperate = CacheTaskOperate.getTaskOperate();
        dbOperate = DbTaskOperate.getTaskOperate();
        if (cacheOperate == null && dbOperate == null) {
            throw new TaskException("cache operate and db operate init failed please check config!");
        }
    }
}
