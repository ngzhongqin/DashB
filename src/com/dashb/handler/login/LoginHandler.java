package com.dashb.handler.login;

import com.dashb.HTTPResponder;
import com.dashb.framework.UUIDGenerator;
import com.dashb.framework.database.PersistenceManager;
import com.dashb.framework.database.dao.SessionDAO;
import com.dashb.framework.database.dao.UserDAO;
import com.dashb.framework.helper.JSONHelper;
import com.dashb.framework.security.PasswordHash;
import com.dashb.framework.vo.SessionVO;
import com.dashb.framework.vo.UserVO;
import com.dashb.router.Router;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.CharsetUtil;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Map;


public class LoginHandler {
    public Logger logger = Logger.getLogger(LoginHandler.class);
    private HTTPResponder httpResponder;
    private LoginJSONHelper loginJSONHelper;
    private PersistenceManager persistenceManager;

    public LoginHandler(PersistenceManager persistenceManager){
        this.httpResponder = new HTTPResponder();
        this.loginJSONHelper = new LoginJSONHelper();
        this.persistenceManager=persistenceManager;
    }

    public void router(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest){

        Router router = new Router(fullHttpRequest.getUri(),persistenceManager);
        String action = router.getAction();
        Map<String,List<String>> params = router.getParameters();
        String session_id = router.getSession();

        if(params.isEmpty()){
            logger.info("No Params");
        }else{

            if("Login".equals(action)){
                logger.info("Action = Login");
                login(ctx, fullHttpRequest);
                return;
            }

            if("Check".equals(action)){
                logger.info("Action = Check");
                checkSession(ctx, fullHttpRequest);
                return;
            }

            if("Logout".equals(action)){
                logger.info("Action = Logout");
                logout(ctx, fullHttpRequest,session_id);
                return;
            }

        }
    }

    private void checkSession(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest) {

    }

    private void login(ChannelHandlerContext ctx, FullHttpRequest req){
        logger.info("Method: login");
        logger.info("content:"+req.content().toString(CharsetUtil.UTF_8));

        String reqString = req.content().toString(CharsetUtil.UTF_8);

        try {
            if(reqString!=null) {
                if(!reqString.isEmpty()) {
                    JSONObject incoming = new JSONObject(reqString);

                    JSONHelper jsonHelper = new JSONHelper();

                    JSONObject data = jsonHelper.getJSONObject(incoming,"data");
                    String lanId = jsonHelper.getString(data, "lanId");
                    String password = jsonHelper.getString(data,"password");

                    UserDAO userDAO = new UserDAO(persistenceManager);
                    UserVO userVO = userDAO.getUser(lanId);
                    logger.info("login: userVO:"+userVO);
                    if(userVO!=null){
                        logger.info("login: userVO: "+userVO.getFull_name());
                    }
                    //check if there is such user
                    if(userVO!=null){
                        boolean isPasswordCorrect = false;
                        try {
                            //validate password
                            PasswordHash passwordHash = new PasswordHash();
                            isPasswordCorrect = passwordHash.validatePassword(password,userVO.getPassword_salt_hash());
                            logger.info("login: lanId:"+lanId+" isPasswordCorrect="+isPasswordCorrect);
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        } catch (InvalidKeySpecException e) {
                            e.printStackTrace();
                        }

                        //if Password is correct
                        if(isPasswordCorrect){
                            UUIDGenerator uuidGenerator = new UUIDGenerator();
                            String new_session_id = uuidGenerator.getUUID();
                            logger.info("login: new_session_id:" + new_session_id);
                            SessionDAO sessionDAO = new SessionDAO(persistenceManager);
                            SessionVO sessionVO = new SessionVO(new_session_id,userVO.getId());
                            sessionDAO.createSession(sessionVO);

                            httpResponder.respond(ctx,req,loginJSONHelper.getJSONLoginSuccess(sessionVO),userVO);
                            return;
                        }

                    }

                    httpResponder.respond(ctx,req,loginJSONHelper.getJSONLoginFailed(),userVO);

                }else{
                    logger.info("INCOMING REQUEST IS EMPTY!");
                }
            }
        } catch (JSONException e) {
            logger.error("incoming reqString that caused error: "+reqString);
            e.printStackTrace();
        }
    }


    private void logout(ChannelHandlerContext ctx, FullHttpRequest req, String session_id){
        logger.info("Method: logout");
        logger.info("content:"+req.content().toString(CharsetUtil.UTF_8));

        String reqString = req.content().toString(CharsetUtil.UTF_8);
        SessionDAO sessionDAO = new SessionDAO(persistenceManager);
        sessionDAO.destroySession(session_id);

        httpResponder.respond(ctx,req,loginJSONHelper.getJSONLogout(),null);

    }

}



