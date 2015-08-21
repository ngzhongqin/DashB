package com.dashb.framework.mapper;

import com.dashb.framework.database.entity.TaskEntity;
import com.dashb.framework.vo.TaskVO;

import java.sql.Date;

/**
 * Created by zhongqinng on 3/8/15.
 * TaskMapper
 */
public class TaskMapper {
    public TaskMapper(){}

    public TaskVO getTaskVO(TaskEntity taskEntity){
        long id = taskEntity.getId();
        String description = taskEntity.getDescription();
        String defect = taskEntity.getDefect();
        String incident = taskEntity.getIncident();
        String status = taskEntity.getStatus();
        String owner = taskEntity.getOwner();
        String remarks = taskEntity.getRemarks();
        Date datedue =taskEntity.getDatedue();

        TaskVO taskVO = new TaskVO();
        taskVO.setDefect(defect);
        taskVO.setDescription(description);
        taskVO.setOwner(owner);
        taskVO.setIncident(incident);
        taskVO.setStatus(status);
        taskVO.setId(id);
        taskVO.setRemarks(remarks);
        taskVO.setDatedue(datedue);

        return taskVO;
    }

    public TaskEntity getTaskEntity(TaskVO taskVO){
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(taskVO.getId());
        taskEntity.setDescription(taskVO.getDescription());
        taskEntity.setStatus(taskVO.getStatus());
        taskEntity.setIncident(taskVO.getIncident());
        taskEntity.setOwner(taskVO.getOwner());
        taskEntity.setDefect(taskVO.getDefect());
        taskEntity.setRemarks(taskVO.getRemarks());
        taskEntity.setDatedue(taskVO.getDatedue());

        return taskEntity;
    }
}
