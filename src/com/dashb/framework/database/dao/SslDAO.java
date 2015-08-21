package com.dashb.framework.database.dao;

import com.dashb.framework.database.PersistenceManager;
import com.dashb.framework.database.entity.SslEntity;
import com.dashb.framework.vo.SslVO;
import org.apache.log4j.Logger;

import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by zhongqinng on 3/8/15.
 * SslDAO
 */
public class SslDAO {
    public Logger logger = Logger.getLogger(SslDAO.class);
    private PersistenceManager persistenceManager;

    public SslDAO(PersistenceManager persistenceManager) {
        this.persistenceManager=persistenceManager;
    }

    public void createNewSsl(SslVO sslVO){
        logger.info("createNewSsl taskVO.id="+sslVO.getId());

        EntityTransaction tx = persistenceManager.getEm().getTransaction();

        SslEntity sslEntity = new SslEntity();

        sslEntity.setPo(sslVO.getPo());
        sslEntity.setPoStatus(sslVO.getPoStatus());
        sslEntity.setCountry(sslVO.getCountry());
        sslEntity.setEnvironment(sslVO.getEnvironment());
        sslEntity.setApplication(sslVO.getApplication());
        sslEntity.setServer(sslVO.getServer());
        sslEntity.setOrganization(sslVO.getOrganization());
        sslEntity.setOrganizational_unit(sslVO.getOrganizational_unit());
        sslEntity.setCommon_name(sslVO.getCommon_name());
        sslEntity.setStart_date(sslVO.getStart_date());
        sslEntity.setEnd_date(sslVO.getEnd_date());
        sslEntity.setDatedue(sslVO.getDatedue());
        sslEntity.setKey_strength(sslVO.getKey_strength());
        sslEntity.setCert_type(sslVO.getCert_type());
        sslEntity.setTeam_involved(sslVO.getTeam_involved());
        sslEntity.setOwner(sslVO.getOwner());

        tx.begin();
        persistenceManager.getEm().persist(sslEntity);
        tx.commit();

    }

    public List<SslEntity> getAllSsl(){
        List<SslEntity> sslEntityList = null;
        try {
            sslEntityList =
                    persistenceManager.getEm()
                            .createQuery("SELECT s FROM SslEntity s")
                            .getResultList();
        }catch (Exception e){
            logger.error("getAllSsl: ERROR: "+e.getMessage());
        }

        return sslEntityList;
    }

    public SslEntity getSsl(long ssl_id) {
        SslEntity sslEntity = null;
        try {
            sslEntity = (SslEntity)
                    persistenceManager.getEm()
                            .createQuery("SELECT t FROM SslEntity t where t.id = :ssl_id ")
                            .setParameter("ssl_id", ssl_id)
                            .getSingleResult();
        }catch (Exception e){
            logger.error("getSsl: ssl_id:"+ssl_id+" ERROR: "+e.getMessage());
        }

        return sslEntity;
    }

    public void updateSsl(SslVO sslVO) {
        logger.info("updateSsl sslVO.id=" + sslVO.getId());

        SslEntity sslEntity = getSsl(sslVO.getId());
        logger.info("updateSsl sslVO.getPo=" + sslVO.getPo());
        sslEntity.setPo(sslVO.getPo());
        logger.info("updateSsl sslVO.getPoStatus=" + sslVO.getPoStatus());
        sslEntity.setPoStatus(sslVO.getPoStatus());
        sslEntity.setCountry(sslVO.getCountry());
        sslEntity.setEnvironment(sslVO.getEnvironment());
        sslEntity.setApplication(sslVO.getApplication());
        sslEntity.setServer(sslVO.getServer());
        sslEntity.setOrganization(sslVO.getOrganization());
        sslEntity.setOrganizational_unit(sslVO.getOrganizational_unit());
        sslEntity.setCommon_name(sslVO.getCommon_name());
        sslEntity.setStart_date(sslVO.getStart_date());
        sslEntity.setEnd_date(sslVO.getEnd_date());
        sslEntity.setDatedue(sslVO.getDatedue());
        sslEntity.setKey_strength(sslVO.getKey_strength());
        sslEntity.setCert_type(sslVO.getCert_type());
        sslEntity.setTeam_involved(sslVO.getTeam_involved());
        sslEntity.setOwner(sslVO.getOwner());

        EntityTransaction tx = persistenceManager.getEm().getTransaction();
        tx.begin();
        persistenceManager.getEm().persist(sslEntity);
        tx.commit();
    }
}
