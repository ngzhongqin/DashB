package com.dashb.handler;

import com.dashb.exception.ExceptionHandler;
import com.dashb.framework.database.dao.SslDAO;
import com.dashb.framework.database.entity.SslEntity;
import com.dashb.framework.helper.SslJSONHelper;
import com.dashb.framework.vo.SslVO;
import com.dashb.framework.vo.UserVO;
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


public class SslHandler {
    public Logger logger = Logger.getLogger(SslHandler.class);
    private HTTPResponder httpResponder;
    private PersistenceManager persistenceManager;
    private JSONHelper jsonHelper;
    private SslJSONHelper sslJSONHelper;

    public SslHandler(PersistenceManager pesistenceManager){
        this.httpResponder = new HTTPResponder();
        this.persistenceManager =pesistenceManager;
        this.jsonHelper = new JSONHelper();
        this.sslJSONHelper = new SslJSONHelper();
    }

    public void router(ChannelHandlerContext ctx, FullHttpRequest req){

        Router router = new Router(req.getUri(),persistenceManager);
        String action = router.getAction();
        int ssl_id = router.getParamInt("ssl");
        UserVO userVO = router.getUser();

        Map<String,List<String>> params = router.getParameters();


        if(params.isEmpty()){


        }else{

            if(userVO!=null){
                if("GetSSLs".equals(action)){
                    logger.info("Action = GetSSLs");
                    getAll(ctx,req);
                    return;
                }

            /* URI = /tasks?action=View&task={number}   */
                if("View".equals(action)){
                    logger.info("Action = View");
                    getSsl(ctx, req, ssl_id);
                    return;
                }

                if("Update".equals(action)){
                    logger.info("Action = Update");
                    updateSsl(ctx, req, ssl_id);
                    return;
                }

                if("New".equals(action)){
                    logger.info("Action = New");
                    newSsl(ctx, req);
                    return;
                }

            }else{
                logger.info("No Session Found: session_id:"+router.getSession());
                ExceptionHandler exceptionHandler = new ExceptionHandler();
                exceptionHandler.handleNoSessionFoundException(ctx, req);
            }
        }
    }

    private void updateSsl(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest, int ssl_id) {
        logger.info("Method: update ssl_id:"+ssl_id);
        String reqString = jsonHelper.getRequestString(fullHttpRequest);
        try {

            boolean check = jsonHelper.checkIfRequestIsEmpty(fullHttpRequest);
            if(!check) {
                JSONObject incoming = new JSONObject(reqString);
                SslVO sslVO = sslJSONHelper.getSslVO(incoming);


                SslDAO sslDAO = new SslDAO(persistenceManager);
                sslDAO.updateSsl(sslVO);


                JSONObject replyJSON = new JSONObject();
                replyJSON.put("CODE", "100");
                httpResponder.respond(ctx, fullHttpRequest, replyJSON);
            }
        } catch (JSONException e) {
            logger.error("incoming reqString that caused error: "+reqString);
            e.printStackTrace();
        }

    }

    private void newSsl(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest){
        logger.info("Method: newSsl");
        logger.info("content:"+fullHttpRequest.content().toString(CharsetUtil.UTF_8));
        String reqString = jsonHelper.getRequestString(fullHttpRequest);
        try {

            boolean check = jsonHelper.checkIfRequestIsEmpty(fullHttpRequest);
            if(!check) {
                JSONObject incoming = new JSONObject(reqString);
                SslVO sslVO = sslJSONHelper.getSslVO(incoming);


                SslDAO sslDAO = new SslDAO(persistenceManager);
                sslDAO.createNewSsl(sslVO);

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

        SslDAO sslDAO = new SslDAO(persistenceManager);
        List<SslEntity> sslEntityList = sslDAO.getAllSsl();
        SslJSONHelper sslJSONHelper = new SslJSONHelper();
        JSONObject replyJSON = sslJSONHelper.getJSONObject(sslEntityList);

        httpResponder.respond(ctx,fullHttpRequest,replyJSON);

    }

    private void getSsl(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest,int ssl_id){
        logger.info("Method: getSsl, ssl_id:"+ssl_id);
        boolean check = jsonHelper.checkIfRequestIsEmpty(fullHttpRequest);

        SslDAO sslDAO = new SslDAO(persistenceManager);
        SslEntity sslEntity = sslDAO.getSsl(ssl_id);

        SslJSONHelper sslJSONHelper = new SslJSONHelper();
        JSONObject replyJSON = sslJSONHelper.getJSONObject(sslEntity);

        httpResponder.respond(ctx,fullHttpRequest,replyJSON);

    }

}
