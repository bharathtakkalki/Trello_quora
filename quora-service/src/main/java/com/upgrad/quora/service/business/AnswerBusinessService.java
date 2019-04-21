package com.upgrad.quora.service.business;


import com.upgrad.quora.service.dao.AnswerDao;
import com.upgrad.quora.service.dao.QuestionDao;
import com.upgrad.quora.service.entity.AnswerEntity;
import com.upgrad.quora.service.entity.QuestionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerBusinessService {

    @Autowired
    QuestionDao questionDao;

    @Autowired
    AnswerDao answerDao;

    public List<AnswerEntity> getAllAnswerToQuestion(final String questionUuid){
        QuestionEntity questionEntity = questionDao.getQuestionByQuestionUuid(questionUuid);

        List<AnswerEntity> answerEntities = answerDao.getAllAnswerToQuestion(questionEntity);

        return answerEntities;
    }

}
