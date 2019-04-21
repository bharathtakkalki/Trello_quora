package com.upgrad.quora.service.dao;


import com.upgrad.quora.service.entity.AnswerEntity;
import com.upgrad.quora.service.entity.QuestionEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AnswerDao {

    @PersistenceContext
    private EntityManager entityManager;


    public List<AnswerEntity> getAllAnswerToQuestion(QuestionEntity questionEntity){
        List<AnswerEntity> answerEntities = entityManager.createNamedQuery("getAllAnswerToQuestion",AnswerEntity.class).setParameter("question",questionEntity).getResultList();
        return answerEntities;
    }
}
