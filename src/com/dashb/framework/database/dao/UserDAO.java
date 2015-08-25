package com.dashb.framework.database.dao;

import com.dashb.framework.database.PersistenceManager;
import com.dashb.framework.database.entity.UserEntity;
import com.dashb.framework.vo.UserVO;
import org.apache.log4j.Logger;
import javax.persistence.EntityTransaction;
import java.util.List;


/**
 * Created by zhongqinng on 26/7/15.
 * UserDAO
 */
public class UserDAO {
    public Logger logger = Logger.getLogger(UserDAO.class);
    private PersistenceManager persistenceManager;

    public UserDAO(PersistenceManager persistenceManager){

        this.persistenceManager = persistenceManager;
    };

    public UserVO createNewUser(UserVO userVO){
        EntityTransaction tx = persistenceManager.getEm().getTransaction();
        UserEntity userEntity = new UserEntity();
        userEntity.setLanId(userVO.getLanId());
        userEntity.setFull_name(userVO.getFull_name());
        userEntity.setPassword_salt_hash(userVO.getPassword_salt_hash());
        tx.begin();
        persistenceManager.getEm().persist(userEntity);
        tx.commit();
        logger.info("createNewUser ID:"+userEntity.getId());
        userVO.setId(userEntity.getId());
        return userVO;
    }

    public UserVO getUser(String lanId){
        UserVO userVO = null;
        try {
            UserEntity userEntity =
                    (UserEntity) persistenceManager.getEm()
                            .createQuery("SELECT u FROM UserEntity u where u.lanId= :lanId")
                            .setParameter("lanId", lanId)
                            .getSingleResult();
            if(userEntity!=null){
                userVO = new UserVO(userEntity.getId(),
                        userEntity.getFull_name(),
                        userEntity.getLanId(),
                        userEntity.getPassword_salt_hash());
            }
        }catch (Exception e){
            logger.error("getUser: ERROR: "+e.getMessage());
        }

        return userVO;
    }

    public UserVO getUserFromSessionId(String sessionId){
        UserVO userVO = null;
        try {
            UserEntity userEntity =
                    (UserEntity) persistenceManager.getEm()
                            .createQuery("SELECT u FROM UserEntity u, SessionEntity s where s.id = :sessionId " +
                                    "and s.user_id = u.id")
                            .setParameter("sessionId", sessionId)
                            .getSingleResult();
            if(userEntity!=null){
                userVO = new UserVO(userEntity.getId(),
                        userEntity.getFull_name(),
                        userEntity.getLanId(),
                        userEntity.getPassword_salt_hash());
            }
        }catch (Exception e){
            logger.error("getUser: ERROR: "+e.getMessage());
        }

        return userVO;
    }

    public boolean checkIfLANIDisTaken(String lanId) {
        boolean returnBoolean = false;

        List<UserEntity> userEntityArrayList =
                persistenceManager.getEm().createQuery("select u from UserEntity u where u.lanId = :lanId")
                        .setParameter("lanId", lanId)
                        .getResultList();

        if(userEntityArrayList.size()>0){
            logger.info("checkIfLANIDisTaken: lanId:"+lanId+" is taken");
            returnBoolean =true;
        }

        return returnBoolean;
    }

    public boolean changePassword(UserVO userVO,String new_password_hash){
        boolean returnBoolean = false;
        UserEntity userEntity =
                (UserEntity) persistenceManager.getEm()
                        .createQuery("SELECT u FROM UserEntity u where u.lanId= :lanId")
                        .setParameter("lanId", userVO.getLanId())
                        .getSingleResult();
        if(userEntity!=null){
            userEntity.setPassword_salt_hash(new_password_hash);
            EntityTransaction tx = persistenceManager.getEm().getTransaction();
            tx.begin();
            persistenceManager.getEm().persist(userEntity);
            tx.commit();
            returnBoolean = true;
        }

        return returnBoolean;
    }
}

