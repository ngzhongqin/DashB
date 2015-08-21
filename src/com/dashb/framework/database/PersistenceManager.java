package com.dashb.framework.database;

import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by zhongqinng on 23/7/15.
 * Persistence Manager
 */
public class PersistenceManager {
    public Logger logger = Logger.getLogger(PersistenceManager.class);
    private EntityManagerFactory entityManagerFactory;
    private EntityManager em;

    public PersistenceManager(){
        entityManagerFactory = Persistence.createEntityManagerFactory("pssdash");
        em = entityManagerFactory.createEntityManager();
    }

    public EntityManager getEm(){
        return em;
    }

}
