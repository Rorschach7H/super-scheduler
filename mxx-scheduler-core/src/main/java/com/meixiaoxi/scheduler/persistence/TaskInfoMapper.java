package com.meixiaoxi.scheduler.persistence;

import com.meixiaoxi.scheduler.persistence.entity.TaskInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TaskInfo record);

    int insertSelective(TaskInfo record);

    TaskInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TaskInfo record);

    int updateByPrimaryKey(TaskInfo record);

    Integer getMaxQueue(@Param("taskGroup") String taskGroup, @Param("executeDate") String executeDate);

    List<TaskInfo> selectByObjectId(@Param("taskGroup") String taskGroup, @Param("executeTime") String executeTime, @Param("objectId") Long objectId);

    /**
     * 查询出非正常任务
     *
     * @return
     */
    List<TaskInfo> selectAbnormalTask();
}