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
        JSONObject jsonObject = new JSONObject();
        JSONObject returnJsonObject = new JSONObject();
        try {
            jsonObject.put("code","SEC-100");
            jsonObject.put("message","Login Successful");
            jsonObject.put("pssdash_session",sessionVO.getId());
            returnJsonObject.put("data",jsonObject);
        } catch (JSONException e) {
            logger.error("getJSONLoginSuccess ERROR:" + e.getMessage());
        }

        return returnJsonObject;
    }

    public JSONObject getJSONLoginFailed(){
        JSONObject jsonObject = new JSONObject();
        JSONObject returnJsonObject = new JSONObject();
        try {
            jsonObject.put("code","SEC-101");
            jsonObject.put("message","Login Failed - Incorrect Email or Password");
            returnJsonObject.put("data",jsonObject);
        } catch (JSONException e) {
            logger.error("getJSONLoginFailed ERROR:" + e.getMessage());
        }

        return returnJsonObject;
    }

    public JSONObject getJSONLogout() {
        JSONObject jsonObject = new JSONObject();
        JSONObject returnJsonObject = new JSONObject();
        try {
            jsonObject.put("code","SEC-103");
            jsonObject.put("message","Logout Successful.");
            returnJsonObject.put("data",jsonObject);
        } catch (JSONException e) {
            logger.error("getJSONLogout ERROR:" + e.getMessage());
        }

        return returnJsonObject;
    }

    public JSONObject getJSONChangePasswordSuccess() {
        JSONObject jsonObject = new JSONObject();
        JSONObject returnJsonObject = new JSONObject();
        try {
            jsonObject.put("code","SEC-105");
            jsonObject.put("message","Change Password Successful");
            returnJsonObject.put("data",jsonObject);
        } catch (JSONException e) {
            logger.error("getJSONLoginSuccess ERROR:" + e.getMessage());
        }

        return returnJsonObject;
    }

    public JSONObject getJSONChangePasswordFailed() {
        JSONObject jsonObject = new JSONObject();
        JSONObject returnJsonObject = new JSONObject();
        try {
            jsonObject.put("code","SEC-106");
            jsonObject.put("message","Change Password Failed");
            returnJsonObject.put("data",jsonObject);
        } catch (JSONException e) {
            logger.error("getJSONLoginSuccess ERROR:" + e.getMessage());
        }

        return returnJsonObject;
    }
}
