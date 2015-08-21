package com.dashb.framework.database.dao;

import com.dashb.framework.database.entity.TaskEntity;
import com.dashb.framework.database.PersistenceManager;
import org.apache.log4j.Logger;
import com.dashb.framework.vo.TaskVO;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by zhongqinng on 26/7/15.
 */
public class TaskDAO {
    public Logger logger = Logger.getLogger(TaskDAO.class);
    private PersistenceManager persistManager;

    public TaskDAO(PersistenceManager pesistenceManager) {
        this.persistManager=pesistenceManager;
    }

    public void createNewTask(TaskVO taskVO){
        logger.info("createNewTask taskVO.id="+taskVO.getId());

        EntityTransaction tx = persistManager.getEm().getTransaction();


        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setDefect(taskVO.getDefect());
        taskEntity.setCreateddt(taskVO.getCreateddt());
        taskEntity.setDescription(taskVO.getDescription());
        taskEntity.setIncident(taskVO.getIncident());
        taskEntity.setOwner(taskVO.getOwner());
        taskEntity.setStatus(taskVO.getStatus());
        taskEntity.setRemarks(taskVO.getRemarks());
        taskEntity.setDatedue(taskVO.getDatedue());

        tx.begin();
        persistManager.getEm().persist(taskEntity);
        tx.commit();

    }

    public List<TaskEntity> getAllTasks(){
        List<TaskEntity> taskEntityArrayList = null;
        try {
            taskEntityArrayList =
                     persistManager.getEm()
                            .createQuery("SELECT t FROM TaskEntity t")
                            .getResultList();
        }catch (Exception e){
            logger.error("getTasks: ERROR: "+e.getMessage());
        }

        return taskEntityArrayList;
    }

    public TaskEntity getTask(long task_id) {
        TaskEntity taskEntity = null;
        try {
            taskEntity = (TaskEntity)
                    persistManager.getEm()
                            .createQuery("SELECT t FROM TaskEntity t where t.id = :task_id ")
                            .setParameter("task_id", task_id)
                            .getSingleResult();
        }catch (Exception e){
            logger.error("getTask: task_id:"+task_id+" ERROR: "+e.getMessage());
        }

        return taskEntity;
    }

    public void updateTask(TaskVO taskVO) {
        logger.info("updateTask taskVO.id="+taskVO.getId());

        TaskEntity taskEntity = getTask(taskVO.getId());

        taskEntity.setDescription(taskVO.getDescription());
        taskEntity.setStatus(taskVO.getStatus());
        taskEntity.setIncident(taskVO.getIncident());
        taskEntity.setOwner(taskVO.getOwner());
        taskEntity.setDefect(taskVO.getDefect());
        taskEntity.setRemarks(taskVO.getRemarks());
        taskEntity.setDatedue(taskVO.getDatedue());

        logger.info("taskEntity id:"+taskEntity.getId());

        EntityTransaction tx = persistManager.getEm().getTransaction();
        tx.begin();
        persistManager.getEm().persist(taskEntity);
        tx.commit();
    }
}
