package com.dashb.handler;

import com.dashb.router.Router;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.CharsetUtil;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.dashb.HTTPResponder;
import com.dashb.framework.database.PersistenceManager;
import com.dashb.framework.database.dao.TaskDAO;
import com.dashb.framework.database.entity.TaskEntity;
import com.dashb.framework.helper.JSONHelper;
import com.dashb.framework.helper.TaskJSONHelper;
import com.dashb.framework.vo.TaskVO;


public class TaskListHandler {
    public Logger logger = Logger.getLogger(TaskListHandler.class);
    private HTTPResponder httpResponder;
    private PersistenceManager persistenceManager;
    private JSONHelper jsonHelper;
    private TaskJSONHelper taskJSONHelper;

    public TaskListHandler(PersistenceManager pesistenceManager){
        this.httpResponder = new HTTPResponder();
        this.persistenceManager =pesistenceManager;
        this.jsonHelper = new JSONHelper();
        this.taskJSONHelper = new TaskJSONHelper();
    }

    public void router(ChannelHandlerContext ctx, FullHttpRequest req){

        Router router = new Router(req.getUri());
        String action = router.getAction();
        int task_id = router.getParamInt("task");
        Map<String,List<String>> params = router.getParameters();


        if(params.isEmpty()){
            /* URI = /tasks/   */
            getAll(ctx,req);
        }else{
            /* URI = /tasks?action=View&task={number}   */
            if("View".equals(action)){
                logger.info("Action = View");
                getTask(ctx,req,task_id);
                return;
            }

            if("Update".equals(action)){
                logger.info("Action = Update");
                updateTask(ctx, req, task_id);
                return;
            }

            if("New".equals(action)){
                logger.info("Action = New");
                newTask(ctx, req);
                return;
            }

        }
    }

    private void updateTask(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest, int task_id) {
        logger.info("Method: updateTask task_id:"+task_id);
        String reqString = jsonHelper.getRequestString(fullHttpRequest);
        try {

            boolean check = jsonHelper.checkIfRequestIsEmpty(fullHttpRequest);
                if(!check) {
                    JSONObject incoming = new JSONObject(reqString);
                    TaskVO taskVO = taskJSONHelper.getTaskVO(incoming);


                    TaskDAO taskDAO = new TaskDAO(persistenceManager);
                    taskDAO.updateTask(taskVO);


                    JSONObject replyJSON = new JSONObject();
                    replyJSON.put("CODE", "100");
                    httpResponder.respond(ctx, fullHttpRequest, replyJSON);
                }
        } catch (JSONException e) {
            logger.error("incoming reqString that caused error: "+reqString);
            e.printStackTrace();
        }

    }

    private void newTask(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest){
        logger.info("Method: newTask");
        logger.info("content:"+fullHttpRequest.content().toString(CharsetUtil.UTF_8));
        String reqString = jsonHelper.getRequestString(fullHttpRequest);
        try {

            boolean check = jsonHelper.checkIfRequestIsEmpty(fullHttpRequest);
            if(!check) {
                JSONObject incoming = new JSONObject(reqString);
                TaskVO taskVO = taskJSONHelper.getTaskVO(incoming);


                TaskDAO taskDAO = new TaskDAO(persistenceManager);
                taskDAO.createNewTask(taskVO);


                JSONObject replyJSON = new JSONObject();
                replyJSON.put("CODE", "100");
                httpResponder.respond(ctx, fullHttpRequest, replyJSON);
            }
        } catch (JSONException e) {
            logger.error("incoming reqString that caused error: "+reqString);
            e.printStackTrace();
        }

    }

    public void getAll(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest){
        logger.info("Method: getAll");

        boolean check = jsonHelper.checkIfRequestIsEmpty(fullHttpRequest);

        TaskDAO taskDAO = new TaskDAO(persistenceManager);
        List<TaskEntity> taskEntityList = taskDAO.getAllTasks();
        TaskJSONHelper taskJSONHelper = new TaskJSONHelper();
        JSONObject replyJSON = taskJSONHelper.getJSONObject(taskEntityList);

        httpResponder.respond(ctx,fullHttpRequest,replyJSON);




    }

    private void getTask(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest,int task_id){
        logger.info("Method: getTask, task_id:"+task_id);
        boolean check = jsonHelper.checkIfRequestIsEmpty(fullHttpRequest);

        TaskDAO taskDAO = new TaskDAO(persistenceManager);
        TaskEntity taskEntity = taskDAO.getTask(task_id);

        TaskJSONHelper taskJSONHelper = new TaskJSONHelper();
        JSONObject replyJSON = taskJSONHelper.getJSONObject(taskEntity);

        httpResponder.respond(ctx,fullHttpRequest,replyJSON);




    }

}
