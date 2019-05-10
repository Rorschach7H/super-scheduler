package com.meixiaoxi.scheduler.persistence;

import com.meixiaoxi.scheduler.persistence.entity.TaskInfo;

import java.util.List;

public interface TaskInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TaskInfo record);

    int insertSelective(TaskInfo record);

    TaskInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TaskInfo record);

    int updateByPrimaryKey(TaskInfo record);

    Integer getMaxQueue(String taskGroup, String executeDate);

    List<TaskInfo> selectByObjectId(String taskGroup, String executeTime, Long objectId);

    /**
     * 查询出非正常任务
     *
     * @return
     */
    List<TaskInfo> selectAbnormalTask();
}