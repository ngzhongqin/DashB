package com.dashb.exception;

import com.dashb.framework.vo.UserVO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dashb.HTTPResponder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public class ExceptionHandler {
	
	 private HTTPResponder httpResponder;

	    public ExceptionHandler(){
	        this.httpResponder = new HTTPResponder();
	    }

	public void handleException(ChannelHandlerContext ctx,FullHttpRequest fullHttpRequest,String code, String message) {
		 JSONObject jsonObjectmain = new JSONObject();
        JSONObject jsonObjectStatus = new JSONObject();
        try {
        	JSONObject jsonObject = new JSONObject();
            jsonObject.put("message",message);
            jsonObject.put("code",code);
            jsonObjectStatus.put("status", jsonObject);
            jsonObjectmain.put("data",jsonObjectStatus);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        httpResponder.respond(ctx,fullHttpRequest,jsonObjectmain);
	}

    public void handleNoSessionFoundException(ChannelHandlerContext ctx,FullHttpRequest fullHttpRequest){
        handleException(ctx, fullHttpRequest, "SEC-104", "No Session Found");
    }

}
