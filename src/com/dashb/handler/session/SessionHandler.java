package com.dashb.handler.session;

import com.dashb.HTTPResponder;
import com.dashb.exception.ExceptionHandler;
import com.dashb.framework.database.PersistenceManager;
import com.dashb.framework.helper.JSONHelper;
import com.dashb.framework.vo.UserVO;
import com.dashb.router.Router;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by zhongqinng on 28/7/15.
 * SessionHandler
 */
public class SessionHandler {
    public Logger logger = Logger.getLogger(SessionHandler.class);
    private HTTPResponder httpResponder;
    private PersistenceManager persistenceManager;
    private JSONHelper jsonHelper;

    public SessionHandler(PersistenceManager persistenceManager){
        this.httpResponder = new HTTPResponder();
        this.persistenceManager=persistenceManager;
        this.jsonHelper=new JSONHelper();
    }


    public void router(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest){

        Router router = new Router(fullHttpRequest.getUri(),persistenceManager);
        String action = router.getAction();
        UserVO userVO = router.getUser();

        if(userVO!=null){
            logger.info("router: action:"+action+" userVO:"+userVO.getFull_name());
        }

        Map<String,List<String>> params = router.getParameters();


        if(params.isEmpty()){
            logger.info("No Params");
        }else{

            if("GetCurrentUser".equals(action)){
                logger.info("Action = GetCurrentUser");
                getCurrentUser(ctx, fullHttpRequest, userVO);
                return;
            }

        }
    }

    public void getCurrentUser(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest, UserVO userVO) {
        logger.info("Method: getCurrentUser");
        boolean check = jsonHelper.checkIfRequestIsEmpty(fullHttpRequest);
        JSONObject replyJSON = new JSONObject();
        if(userVO==null){
            ExceptionHandler exceptionHandler = new ExceptionHandler();
            exceptionHandler.handleNoSessionFoundException(ctx,fullHttpRequest);
        }else{
            httpResponder.respond(ctx,fullHttpRequest,replyJSON, userVO);
        }


    }

}
