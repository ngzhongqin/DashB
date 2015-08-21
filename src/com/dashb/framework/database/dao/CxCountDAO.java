package com.dashb.framework.database.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.dashb.framework.database.PersistenceManager;
import com.dashb.framework.database.entity.CxCountEntity;
import com.dashb.framework.vo.TxnCountVO;


public class CxCountDAO {
    public Logger logger = Logger.getLogger(CxCountDAO.class);
    private PersistenceManager pesistenceManager;

    public CxCountDAO(PersistenceManager pesistenceManager){
    	try{
    		this.pesistenceManager = pesistenceManager;
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    };

    public void createNewTask(TxnCountVO objTxnCountVO){
        EntityTransaction tx = pesistenceManager.getEm().getTransaction();
        CxCountEntity objTxnCountEntity = new CxCountEntity();
        tx.begin();
        pesistenceManager.getEm().persist(objTxnCountEntity);
        tx.commit();
    }
    
    
    public List<CxCountEntity> findAllTxnCount() throws Exception {
		List<CxCountEntity> prodList = new ArrayList<CxCountEntity>();
		String accquery = "select t from CxCountEntity t";
		Query q = pesistenceManager.getEm().createQuery(accquery);
		prodList = q.getResultList();
		return prodList;
    }
    
    
    public List<CxCountEntity> findCountByMon(String monYear) throws Exception {
		List<CxCountEntity> prodList = new ArrayList<CxCountEntity>();
		String accquery = "select t from CxCountEntity t where t.monyear:= monyear";
		Query q = pesistenceManager.getEm().createQuery(accquery);
		q.setParameter("monyear", monYear);
		prodList = q.getResultList();
		return prodList;
    }

}
