package com.dashb.framework.helper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.dashb.framework.database.entity.SslEntity;
import com.dashb.framework.mapper.SslMapper;
import com.dashb.framework.vo.SslVO;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dashb.framework.database.entity.TaskEntity;
import com.dashb.framework.vo.TaskVO;

/**
 * Created by zhongqinng on 27/7/15.
 * SslJSONHelper
 */
public class SslJSONHelper {
    public Logger logger = Logger.getLogger(SslJSONHelper.class);

    public SslJSONHelper(){};

    public ArrayList<SslVO> getSslVOList(List<SslEntity> sslEntityList){
        ArrayList<SslVO> sslVOArrayList = new ArrayList<SslVO>();
        int size = sslEntityList.size();
        int i = 0;
        while(i<size){
            SslMapper sslMapper = new SslMapper();
            SslVO sslVO = sslMapper.getSslVO(sslEntityList.get(i));
            sslVOArrayList.add(sslVO);
            i++;
        }
        return sslVOArrayList;
    }

    public JSONObject getJSONObject(List<SslEntity> sslEntityList){
        JSONArray jsonArray = new JSONArray();
        int size = sslEntityList.size();
        int i = 0;
        while(i<size){
            JSONObject sslJSON = loadSslEntityIntoJSON(sslEntityList.get(i));
            jsonArray.put(sslJSON);
            i++;
        }
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("data", jsonArray);

        } catch (JSONException e) {
            logger.error("getJSONObject: error:"+e.getMessage());
            e.printStackTrace();
        }

        return jsonObject;
    }

    private JSONObject loadSslEntityIntoJSON(SslEntity sslEntity){
        JSONObject sslJSON = new JSONObject();
        try {

            sslJSON.put("id",sslEntity.getId());
            sslJSON.put("po",sslEntity.getPo());
            sslJSON.put("poStatus",sslEntity.getPoStatus());
            sslJSON.put("country",sslEntity.getCountry());
            sslJSON.put("environment",sslEntity.getEnvironment());
            sslJSON.put("application",sslEntity.getApplication());
            sslJSON.put("server",sslEntity.getServer());
            sslJSON.put("organization",sslEntity.getOrganization());
            sslJSON.put("organizational_unit",sslEntity.getOrganizational_unit());
            sslJSON.put("common_name",sslEntity.getCommon_name());
            sslJSON.put("start_date",sslEntity.getStart_date());
            sslJSON.put("end_date",sslEntity.getEnd_date());
            sslJSON.put("datedue",sslEntity.getDatedue());
            sslJSON.put("key_strength",sslEntity.getKey_strength());
            sslJSON.put("cert_type",sslEntity.getCert_type());
            sslJSON.put("team_involved",sslEntity.getTeam_involved());
            sslJSON.put("owner",sslEntity.getOwner());


        } catch (JSONException e) {
            logger.error("loadSslEntityIntoJSON : error:"+e.getMessage());
            e.printStackTrace();
        }
        return sslJSON;
    }

    public JSONObject getJSONObject(SslEntity sslEntity) {
        JSONObject sslJSON = loadSslEntityIntoJSON(sslEntity);
        JSONObject returnJSONObject = new JSONObject();
        try {
            returnJSONObject.put("data",sslJSON);
        } catch (JSONException e) {
            logger.error("getJSONObject Single: error:"+e.getMessage());
            e.printStackTrace();
        }

        return returnJSONObject;
    }

    public SslVO getSslVO(JSONObject incoming){
        JSONHelper jsonHelper = new JSONHelper();

        JSONObject data = jsonHelper.getJSONObject(incoming, "data");


        long id = jsonHelper.getInt(data, "id");
        String po = jsonHelper.getString(data, "po");
        logger.info("getSslVO: po:"+po);
        String poStatus = jsonHelper.getString(data, "poStatus");
        logger.info("getSslVO: poStatus:"+poStatus);
        String country = jsonHelper.getString(data, "country");
        String environment = jsonHelper.getString(data, "environment");
        String application = jsonHelper.getString(data, "application");
        String server = jsonHelper.getString(data, "server");
        String organization = jsonHelper.getString(data, "organization");
        String organizational_unit = jsonHelper.getString(data, "organizational_unit");
        String common_name = jsonHelper.getString(data, "common_name");
        Date start_date  = jsonHelper.getDate(data, "start_date");
        Date end_date = jsonHelper.getDate(data, "end_date");
        Date datedue = jsonHelper.getDate(data, "datedue");
        String key_strength = jsonHelper.getString(data, "key_strength");
        String cert_type = jsonHelper.getString(data, "cert_type");
        String team_involved = jsonHelper.getString(data, "team_involved");
        String owner = jsonHelper.getString(data, "owner");


        logger.info("getSslVO: po:"+po);

        SslVO sslVO = new SslVO();

        sslVO.setId(id);
        sslVO.setPo(po);
        sslVO.setPoStatus(poStatus);
        sslVO.setCountry(country);
        sslVO.setEnvironment(environment);
        sslVO.setApplication(application);
        sslVO.setServer(server);
        sslVO.setOrganization(organization);
        sslVO.setOrganizational_unit(organizational_unit);
        sslVO.setCommon_name(common_name);
        sslVO.setStart_date(start_date);
        sslVO.setEnd_date(end_date);
        sslVO.setDatedue(datedue);
        sslVO.setKey_strength(key_strength);
        sslVO.setCert_type(cert_type);
        sslVO.setTeam_involved(team_involved);
        sslVO.setOwner(owner);

        return sslVO;
    }
}
