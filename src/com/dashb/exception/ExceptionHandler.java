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

	public void handleException(ChannelHandlerContext ctx,FullHttpRequest fullHttpRequest,String code, String message, String colour) {
        JSONObject jsonObjectStatus = new JSONObject();
        try {
        	JSONObject jsonObject = new JSONObject();
            jsonObject.put("message",message);
            jsonObject.put("code",code);
            jsonObject.put("colour",colour);
            jsonObjectStatus.put("returnStatus", jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        httpResponder.respond(ctx,fullHttpRequest,jsonObjectStatus);
	}

    public void handleNoSessionFoundException(ChannelHandlerContext ctx,FullHttpRequest fullHttpRequest){
        handleException(ctx, fullHttpRequest, "SEC-104", "No Session Found", "R");
    }

    public void handleUncaughtException(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest) {
        handleException(ctx, fullHttpRequest, "PSS-119", "UncaughtException", "R");
    }

    public void handleNoServiceFoundException(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest){
        handleException(ctx, fullHttpRequest,"PSS-119","Service Not found","R");
    }


}
