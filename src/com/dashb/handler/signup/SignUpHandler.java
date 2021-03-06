package com.dashb.handler.signup;

import com.dashb.HTTPResponder;
import com.dashb.framework.UUIDGenerator;
import com.dashb.framework.database.PersistenceManager;
import com.dashb.framework.database.dao.SessionDAO;
import com.dashb.framework.database.dao.UserDAO;
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


public class SignUpHandler {
    public Logger logger = Logger.getLogger(SignUpHandler.class);
    private HTTPResponder httpResponder;
    private PersistenceManager persistenceManager;

    public SignUpHandler(PersistenceManager persistenceManager){
        this.httpResponder = new HTTPResponder();
        this.persistenceManager=persistenceManager;
    }

    public void router(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest){

        Router router = new Router(fullHttpRequest.getUri(),persistenceManager);
        String action = router.getAction();
        Map<String,List<String>> params = router.getParameters();


        if(params.isEmpty()){
            logger.info("No Params");
        }else{

            if("SignUp".equals(action)){
                logger.info("Action = SignUp");
                signUp(ctx, fullHttpRequest);
                return;
            }

        }
    }

    public void signUp(ChannelHandlerContext ctx, FullHttpRequest req){
        logger.info("Method: signUp");
        logger.info("content:"+req.content().toString(CharsetUtil.UTF_8));

        String reqString = req.content().toString(CharsetUtil.UTF_8);
        UserVO userVO = null;
        String code = "SGU-101";
        String message = "Sign Up was unsuccessful.";
        String pssdash_session = null;

        try {
            if(reqString!=null) {
                if(!reqString.isEmpty()) {
                    JSONObject incoming = new JSONObject(reqString);
                    JSONObject data = (JSONObject) incoming.get("data");

                    String lanid = (String) data.get("lanId");
                    String password = (String) data.get("password");
                    String full_name = (String) data.get("full_name");


                    UserDAO userDAO = new UserDAO(persistenceManager);
                    boolean checkIfLANIDistaken = userDAO.checkIfLANIDisTaken(lanid);
                    //boolean checkIfEmailIsTaken = userDAO.checkIfEmailIsTaken(email);
                    if(!checkIfLANIDistaken){
                        String password_salt_hash;

                        PasswordHash passwordHash = new PasswordHash();
                        try {

                            password_salt_hash=passwordHash.createHash(password);
                            userVO = new UserVO(full_name,lanid,password_salt_hash);
                            userVO = userDAO.createNewUser(userVO);

                            UUIDGenerator uuidGenerator = new UUIDGenerator();
                            String new_session_id = uuidGenerator.getUUID();
                            logger.info("signup: new_session_id:" + new_session_id);
                            SessionDAO sessionDAO = new SessionDAO(persistenceManager);
                            SessionVO sessionVO = new SessionVO(new_session_id,userVO.getId());
                            sessionDAO.createSession(sessionVO);

                            code = "SGU-100";
                            message = "Sign Up successful.";
                            pssdash_session = sessionVO.getId();

                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        } catch (InvalidKeySpecException e) {
                            e.printStackTrace();
                        }
                    }

                    respond(ctx, req, code, message, pssdash_session,userVO);


                }else{
                    logger.info("INCOMING REQUEST IS EMPTY!");
                }
            }
        } catch (JSONException e) {
            logger.error("incoming reqString that caused error: "+reqString);
            e.printStackTrace();
        }
    }

    private void respond(ChannelHandlerContext ctx, FullHttpRequest req, String code, String message, String pssdash_session, UserVO userVO){
        JSONObject returnStatusJsonObject = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        JSONObject mainJsonObject = new JSONObject();
        try {
            returnStatusJsonObject.put("code",code);
            returnStatusJsonObject.put("message", message);

            if(pssdash_session!=null){
                jsonObject.put("pssdash_session",pssdash_session);
            }

            mainJsonObject.put("data",jsonObject);
            mainJsonObject.put("returnStatus",returnStatusJsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        httpResponder.respond(ctx,req,mainJsonObject,userVO);

    }

}
