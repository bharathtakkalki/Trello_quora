package com.upgrad.quora.service.dao;


import com.upgrad.quora.service.entity.UsersEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SuppressWarnings("JpaQueryApiInspection")
@Repository
public class AdminDao {

    @PersistenceContext
    private EntityManager entityManager;

    public UsersEntity deleteUser(final String uuid){
        UsersEntity user = entityManager.createNamedQuery("userByUuid",UsersEntity.class).setParameter("uuid",uuid).getSingleResult();
        entityManager.remove(user);
        return user;
    }


}
