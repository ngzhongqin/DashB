package com.dashb.framework.helper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.dashb.framework.mapper.TaskMapper;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dashb.framework.database.entity.TaskEntity;
import com.dashb.framework.vo.TaskVO;

/**
 * Created by zhongqinng on 27/7/15.
 * TaskJSONHelper
 */
public class TaskJSONHelper {
    public Logger logger = Logger.getLogger(TaskJSONHelper.class);

    public TaskJSONHelper(){};



    public ArrayList<TaskVO> getTaskVOList(List<TaskEntity> taskEntityList){
        ArrayList<TaskVO> taskVOList = new ArrayList<TaskVO>();
        int size = taskEntityList.size();
        int i = 0;
        while(i<size){
            TaskMapper taskMapper = new TaskMapper();
            TaskVO taskVO = taskMapper.getTaskVO(taskEntityList.get(i));
            taskVOList.add(taskVO);
            i++;
        }
        return taskVOList;
    }

    public JSONObject getJSONObject(List<TaskEntity> taskEntityList){
        JSONArray jsonArray = new JSONArray();
        int size = taskEntityList.size();
        int i = 0;
        while(i<size){
            JSONObject taskJSON = loadTaskEntityIntoJSON(taskEntityList.get(i));
            jsonArray.put(taskJSON);
            i++;
        }
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("data", jsonArray);

        } catch (JSONException e) {
            logger.error("getJSONObject: error:"+e.getMessage());
            e.printStackTrace();
        }

        return jsonObject;
    }

    private JSONObject loadTaskEntityIntoJSON(TaskEntity taskEntity){
        JSONObject taskJSON = new JSONObject();
        try {
            taskJSON.put("no",taskEntity.getId());
            taskJSON.put("description",taskEntity.getDescription());
            taskJSON.put("defect", taskEntity.getDefect());
            taskJSON.put("incident", taskEntity.getIncident());
            taskJSON.put("status", taskEntity.getStatus());
            taskJSON.put("owner", taskEntity.getOwner());
            taskJSON.put("remarks",taskEntity.getRemarks());
            taskJSON.put("datedue",taskEntity.getDatedue());
            taskJSON.put("datedueFormat",taskEntity.getDatedue());

        } catch (JSONException e) {
            logger.error("loadTaskEntityIntoJSON : error:"+e.getMessage());
            e.printStackTrace();
        }
        return taskJSON;
    }

    public JSONObject getJSONObject(TaskEntity taskEntity) {
        JSONObject taskJSON = loadTaskEntityIntoJSON(taskEntity);
        JSONObject returnJSONObject = new JSONObject();
        try {
            returnJSONObject.put("data",taskJSON);
        } catch (JSONException e) {
            logger.error("getJSONObject Single: error:"+e.getMessage());
            e.printStackTrace();
        }

        return returnJSONObject;
    }

    public TaskVO getTaskVO(JSONObject incoming){
        JSONHelper jsonHelper = new JSONHelper();

        JSONObject data = jsonHelper.getJSONObject(incoming, "data");
        String description = jsonHelper.getString(data, "description");
        String incident = jsonHelper.getString(data,"incident");
        String defect = jsonHelper.getString(data,"defect");
        String status = jsonHelper.getString(data,"status");
        String owner = jsonHelper.getString(data,"owner");
        String remarks = jsonHelper.getString(data, "remarks");
        Date datedue = jsonHelper.getDate(data, "datedue");

        logger.info("getTaskVO datedue:"+datedue);

        int id = jsonHelper.getInt(data, "no");

        TaskVO taskVO = new TaskVO();
        taskVO.setStatus(status);
        taskVO.setIncident(incident);
        taskVO.setDescription(description);
        taskVO.setOwner(owner);
        taskVO.setDefect(defect);
        taskVO.setRemarks(remarks);
        taskVO.setDatedue(datedue);

        taskVO.setId(id);

        return taskVO;
    }
}
