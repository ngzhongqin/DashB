package com.dashb.router;

/**
 * Created by zhongqinng on 15/6/15.
 */
public class UriPathHelper {
    public UriPathHelper(){

    }

    public UriPath getUriPath(String uriPathString){
        UriPath uriPath = UriPath.INVALID;
        if(uriPathString!=null){
            if(uriPathString.toLowerCase().equals("/tasks")){
                uriPath=UriPath.TASKS;
            }
        }

        return uriPath;
    }
}
