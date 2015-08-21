package com.dashb.framework.helper;

import java.sql.Date;
import java.text.SimpleDateFormat;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.CharsetUtil;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhongqinng on 27/7/15.
 * JSONHelper
 */
public class JSONHelper {
    public Logger logger = Logger.getLogger(JSONHelper.class);

    public JSONHelper(){};

    public String getString(JSONObject jsonObject, String key){
        String returnString = null;
            if(key!=null){
                if(jsonObject!=null){
                    try{
                        returnString = jsonObject.getString(key);
                    }catch (Exception e){
                        logger.error("getString: Exception: "+e.getMessage());
                    }
                }else{
                    logger.error("getString: jsonObject:"+jsonObject+" is null");
                }
            }else{
                logger.error("getString: key:"+key+" is null");
            }

        return returnString;
    }

    public Date getDate (JSONObject jsonObject, String key){
        Date datedue = null;
        if(key!=null){
            if(jsonObject!=null){
                try{
                    //1912-03-11T16:00:00.000Z
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                    String dateString = (String) jsonObject.get(key);
                    java.util.Date date = sdf.parse(dateString);
                    datedue = new Date(date.getTime());
                    logger.info("dateString: dateString:" + dateString);
                    logger.info("java.util.Date date="+date);

                }catch (Exception e){
                    logger.error("getDate: Exception: "+e.getMessage());
                }
            }else{
                logger.error("getString: jsonObject:"+jsonObject+" is null");
            }
        }else{
            logger.error("getString: key:"+key+" is null");
        }

        return datedue;
    }

    public JSONObject getJSONObject(JSONObject incoming, String key){
        JSONObject returnJSONObject = null;

        try {
            returnJSONObject = (JSONObject) incoming.get(key);
        } catch (JSONException e) {
            logger.error("getJSONObject: Exception: " + e.getMessage());
        }

        return returnJSONObject;
    }

    public int getInt(JSONObject jsonObject, String key) {
        int returnInt = -1;
        if(key!=null){
            if(jsonObject!=null){
                try{
                    returnInt = jsonObject.getInt(key);
                }catch (Exception e){
                    logger.error("getString: Exception: "+e.getMessage());
                }
            }else{
                logger.error("getString: jsonObject:"+jsonObject+" is null");
            }
        }else{
            logger.error("getString: key:"+key+" is null");
        }

        return returnInt;
    }

    public boolean checkIfRequestIsEmpty(FullHttpRequest fullHttpRequest){
        logger.info("checkIfRequestIsEmpty");
        String reqString = fullHttpRequest.content().toString(CharsetUtil.UTF_8);
        boolean returnBool = true;
        try {
            if(reqString!=null) {
                if(!reqString.isEmpty()) {
                    JSONObject incoming = new JSONObject(reqString);
                    returnBool = false;

                }else{
                    logger.info("INCOMING REQUEST IS EMPTY");
                }
            }
        } catch (JSONException e) {
            logger.error("incoming reqString that caused error: "+reqString);
            e.printStackTrace();
        }
        return returnBool;
    }

    public String getRequestString(FullHttpRequest fullHttpRequest){
        logger.info("getRequestString content:"+fullHttpRequest.content().toString(CharsetUtil.UTF_8));
        String reqString = fullHttpRequest.content().toString(CharsetUtil.UTF_8);
        return reqString;
    }
}
