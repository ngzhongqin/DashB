package com.dashb.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.CharsetUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dashb.HTTPResponder;
import com.dashb.exception.ExceptionHandler;
import com.dashb.framework.database.PersistenceManager;
import com.dashb.framework.database.dao.CountBySegDAO;
import com.dashb.framework.database.dao.CxCountDAO;
import com.dashb.framework.database.entity.CountBySegEntity;
import com.dashb.framework.database.entity.CxCountEntity;


public class CustStatisticsHandler {
    public Logger logger = Logger.getLogger(CustStatisticsHandler.class);
    private HTTPResponder httpResponder;
    private PersistenceManager pesistenceManager;
    
    public CustStatisticsHandler(PersistenceManager pesistenceManager){
        this.httpResponder = new HTTPResponder();
        this.pesistenceManager=pesistenceManager;
    }

    public void getAll(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest){
        logger.info("Method: getAll");
        logger.info("content:"+fullHttpRequest.content().toString(CharsetUtil.UTF_8));
        String reqString = fullHttpRequest.content().toString(CharsetUtil.UTF_8);
        String monYear = null;
        HashMap<String, String> hmTotalCx = new HashMap<String,String>();
        try {
            if(reqString!=null) {
                if(!reqString.isEmpty()) {
                    JSONObject incoming = new JSONObject(reqString);
                    JSONObject user = (JSONObject) incoming.get("cust_stat_params");
                    monYear = (String) user.get("req_month");
                }else{
                    logger.info("this is normal. incoming request is empty");
                }
            }
        } catch (JSONException e) {
            logger.error("incoming reqString that caused error: "+reqString);
            e.printStackTrace();
            (new ExceptionHandler()).handleUncaughtException(ctx, fullHttpRequest);
        } catch (Exception e){
        	logger.error("incoming reqString that caused error: "+reqString);
            e.printStackTrace();
            (new ExceptionHandler()).handleUncaughtException(ctx, fullHttpRequest);
        }


        JSONObject jsonObject = null;
        JSONArray jsonArray = new JSONArray();
        
        try {
        	CountBySegDAO objActCustDAO = new CountBySegDAO(pesistenceManager);
        	CxCountDAO objTxnCountDAO = new CxCountDAO(pesistenceManager);
        	List<CountBySegEntity> arlActCust ;
        	List<CxCountEntity> arlTxnCount ;
        	
        	if(null == monYear || monYear.length() == 0){
        		logger.info("No input parameters fetching full list >>>>>");
        		arlActCust = (List<CountBySegEntity>) objActCustDAO.findAllActCust();
        		logger.info("Size of the list >>>>>"+arlActCust.size());
        		arlTxnCount = (List<CxCountEntity>) objTxnCountDAO.findAllTxnCount();
       	 		logger.info("Size of the list >>>>>"+arlTxnCount.size());
        	}else{
        		logger.info("Input parameters fetching list >>>>>"+monYear);
        		arlActCust = (List<CountBySegEntity>) objActCustDAO.findCountByMon(monYear);
        		logger.info("Size of the list >>>>>"+arlActCust.size());
        		arlTxnCount = (List<CxCountEntity>) objTxnCountDAO.findCountByMon(monYear);
       	 		logger.info("Size of the list >>>>>"+arlTxnCount.size());
        	}
        	
        	if(null != arlActCust && arlActCust.size() > 0){
        		Iterator<CountBySegEntity> itrBySeg = arlActCust.iterator();
        		CountBySegEntity objCountBySegEntity;
        		//float totalCx = 0;
        		String tempTotCx; 
        		
        		while(itrBySeg.hasNext()){
        			objCountBySegEntity = itrBySeg.next();
        			 jsonObject = new JSONObject();
            		 jsonObject.put("MonSeg_"+String.valueOf(objCountBySegEntity.getMonyear())+"_"+String.valueOf(objCountBySegEntity.getSegment()),String.valueOf(objCountBySegEntity.getSegmentdesc())+" | "+String.valueOf(objCountBySegEntity.getTotalact())+" | "+String.valueOf(objCountBySegEntity.getActinmonth()));
            		 
            		 if(objCountBySegEntity.getTotalact() != null){
            			 
            			 //totalCx = totalCx + ft.floatValue(); 
            			 if(hmTotalCx.containsKey("TotCx_"+String.valueOf(objCountBySegEntity.getMonyear()))){
            				 tempTotCx = hmTotalCx.get("TotCx_"+String.valueOf(objCountBySegEntity.getMonyear()));
            				 Float hmVal = Float.valueOf(tempTotCx);
            				 Float newVal = Float.valueOf(objCountBySegEntity.getTotalact());
            				 newVal = newVal + hmVal;
            				 hmTotalCx.put("TotCx_"+String.valueOf(objCountBySegEntity.getMonyear()), String.valueOf(newVal));
            			 }else{
            				 hmTotalCx.put("TotCx_"+String.valueOf(objCountBySegEntity.getMonyear()), String.valueOf(objCountBySegEntity.getTotalact()));
            			 }
            		 }
            		 jsonArray.put(jsonObject);
        		}
        	}
       	 	
        	if(hmTotalCx != null && !hmTotalCx.isEmpty()){
        		Iterator itrHM = hmTotalCx.keySet().iterator();
        		while(itrHM.hasNext()){
        			String key = (String) itrHM.next();
        			jsonObject = new JSONObject();
            		jsonObject.put(key, hmTotalCx.get(key));
            		jsonArray.put(jsonObject);
        		}
        	}
        	
        	if(null != arlTxnCount && arlTxnCount.size() > 0){
        		Iterator itr = arlTxnCount.iterator();
        		CxCountEntity objCxCountEntity;
        		while(itr.hasNext()){
        			objCxCountEntity = (CxCountEntity) itr.next();
        			jsonObject = new JSONObject();
            		jsonObject.put(objCxCountEntity.getMonyear()+ "_ActiveCustomers", String.valueOf(objCxCountEntity.getPerformedtxn()));
            		jsonObject.put(objCxCountEntity.getMonyear()+ "_NewCustomers", String.valueOf(objCxCountEntity.getNewcxcount()));
            		jsonArray.put(jsonObject);
        		}
        	}
        	
        } catch (JSONException e) {
            e.printStackTrace();
        }catch(Exception e){
        	e.printStackTrace();
        }

        JSONObject jsonObjectmain = new JSONObject();
        try {
            jsonObjectmain.put("data", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        httpResponder.respond(ctx,fullHttpRequest,jsonObjectmain);
    }

}
