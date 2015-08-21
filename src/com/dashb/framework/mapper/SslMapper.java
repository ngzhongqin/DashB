package com.dashb.framework.mapper;

import com.dashb.framework.database.entity.SslEntity;
import com.dashb.framework.vo.SslVO;

import java.sql.Date;

/**
 * Created by zhongqinng on 3/8/15.
 * SslMapper
 */
public class SslMapper {
    public SslMapper(){}

    public SslVO getSslVO(SslEntity sslEntity){
        long id = sslEntity.getId();
        String po = sslEntity.getPo();
        String poStatus = sslEntity.getPoStatus() ;
        String country  = sslEntity.getCountry();
        String environment = sslEntity.getEnvironment();
        String application = sslEntity.getApplication();
        String server = sslEntity.getServer();
        String organization = sslEntity.getOrganization();
        String organizational_unit = sslEntity.getOrganizational_unit();
        String common_name = sslEntity.getCommon_name();
        Date start_date = sslEntity.getStart_date();
        Date end_date = sslEntity.getEnd_date();
        Date datedue = sslEntity.getDatedue();
        String key_strength = sslEntity.getKey_strength();
        String cert_type = sslEntity.getCert_type();
        String team_involved = sslEntity.getTeam_involved();
        String owner = sslEntity.getOwner();


        SslVO sslVO = new SslVO();
        sslVO.setId(id);
        sslVO.setPo(po);
        sslVO.setPo(poStatus);
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
