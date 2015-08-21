package com.dashb.framework.database.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.dashb.framework.database.PersistenceManager;
import com.dashb.framework.database.entity.CountBySegEntity;
import com.dashb.framework.vo.ActCustVO;


public class CountBySegDAO {
	
    public Logger logger = Logger.getLogger(CountBySegDAO.class);
    private PersistenceManager pesistenceManager;

    public CountBySegDAO(PersistenceManager pesistenceManager){
    	try{
    		this.pesistenceManager = pesistenceManager;
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    };

    public void createNewTask(ActCustVO objActCustVO){
        EntityTransaction tx = pesistenceManager.getEm().getTransaction();
        CountBySegEntity objActCustEntity = new CountBySegEntity();

        
        tx.begin();
        pesistenceManager.getEm().persist(objActCustEntity);
        tx.commit();
    }
    
    
    public List<CountBySegEntity> findAllActCust() throws Exception {
		List<CountBySegEntity> prodList = new ArrayList<CountBySegEntity>();
		String accquery = "select u from CountBySegEntity u";
		Query q = pesistenceManager.getEm().createQuery(accquery);
		prodList = q.getResultList();
		return prodList;
    }

    
    public List<CountBySegEntity> findCountByMon(String monYear) throws Exception {
		List<CountBySegEntity> prodList = new ArrayList<CountBySegEntity>();
		String accquery = "select u from CountBySegEntity u where u.monyear := monyear";
		Query q = pesistenceManager.getEm().createQuery(accquery);
		q.setParameter("monyear", monYear);
		prodList = q.getResultList();
		return prodList;
    }
    
}
