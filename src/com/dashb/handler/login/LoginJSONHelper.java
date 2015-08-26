package com.dashb.handler.login;

import com.dashb.framework.helper.JSONHelper;
import com.dashb.framework.vo.SessionVO;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhongqinng on 27/7/15.
 * LoginJSONHelper
 */
public class LoginJSONHelper {
    public Logger logger = Logger.getLogger(LoginJSONHelper.class);
    private JSONHelper jsonHelper;
    public LoginJSONHelper(){
        jsonHelper = new JSONHelper();
    }

    public JSONObject getJSONLoginSuccess(SessionVO sessionVO){
        JSONObject returnStatusJSONObject = new JSONObject();
        JSONObject dataJSONObject = new JSONObject();

        JSONObject jsonObject = new JSONObject();
        JSONObject returnJsonObject = new JSONObject();

        try {
            returnStatusJSONObject.put("colour","G");
            returnStatusJSONObject.put("code","SEC-100");
            returnStatusJSONObject.put("message","Login Successful");

            dataJSONObject.put("pssdash_session",sessionVO.getId());

            jsonObject.put("data",dataJSONObject);
            jsonObject.put("returnStatus",returnStatusJSONObject);

            //returnJsonObject.put("data",jsonObject);

        } catch (JSONException e) {
            logger.error("getJSONLoginSuccess ERROR:" + e.getMessage());
        }

        return jsonObject;
    }

    public JSONObject getJSONLoginFailed(){
        JSONObject returnStatusJSONObject = new JSONObject();
        JSONObject dataJSONObject = new JSONObject();

        JSONObject jsonObject = new JSONObject();
        JSONObject returnJsonObject = new JSONObject();
        try {
            returnStatusJSONObject.put("colour","R");
            returnStatusJSONObject.put("code","SEC-101");
            returnStatusJSONObject.put("message","Login Failed - Incorrect Email or Password");
            jsonObject.put("returnStatus",returnStatusJSONObject);

            //returnJsonObject.put("data",jsonObject);
        } catch (JSONException e) {
            logger.error("getJSONLoginFailed ERROR:" + e.getMessage());
        }

        return jsonObject;
    }

    public JSONObject getJSONLogout() {
        JSONObject returnStatusJSONObject = new JSONObject();
        JSONObject dataJSONObject = new JSONObject();

        JSONObject jsonObject = new JSONObject();
        JSONObject returnJsonObject = new JSONObject();
        try {
            returnStatusJSONObject.put("colour","G");
            returnStatusJSONObject.put("code","SEC-103");
            returnStatusJSONObject.put("message","Logout Successful.");

            jsonObject.put("returnStatus",returnStatusJSONObject);

//            returnJsonObject.put("data",jsonObject);
        } catch (JSONException e) {
            logger.error("getJSONLogout ERROR:" + e.getMessage());
        }

        return jsonObject;
    }

    public JSONObject getJSONChangePasswordSuccess() {
        JSONObject returnStatusJSONObject = new JSONObject();
        JSONObject dataJSONObject = new JSONObject();

        JSONObject jsonObject = new JSONObject();
        JSONObject returnJsonObject = new JSONObject();
        try {
            returnStatusJSONObject.put("colour","G");
            returnStatusJSONObject.put("code","SEC-105");
            returnStatusJSONObject.put("message","Change Password Successful");

            jsonObject.put("returnStatus",returnStatusJSONObject);

           // returnJsonObject.put("data",jsonObject);
        } catch (JSONException e) {
            logger.error("getJSONLoginSuccess ERROR:" + e.getMessage());
        }

        return jsonObject;
    }

    public JSONObject getJSONChangePasswordFailed() {
        JSONObject returnStatusJSONObject = new JSONObject();
        JSONObject dataJSONObject = new JSONObject();

        JSONObject jsonObject = new JSONObject();
        JSONObject returnJsonObject = new JSONObject();
        try {
            returnStatusJSONObject.put("code","SEC-106");
            returnStatusJSONObject.put("message","Change Password Failed");
            returnStatusJSONObject.put("colour","R");

            jsonObject.put("returnStatus",returnStatusJSONObject);

           // returnJsonObject.put("data",jsonObject);
        } catch (JSONException e) {
            logger.error("getJSONLoginSuccess ERROR:" + e.getMessage());
        }

        return jsonObject;
    }
}
