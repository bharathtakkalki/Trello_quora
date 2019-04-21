package com.upgrad.quora.api.controller;


import com.upgrad.quora.api.model.QuestionDetailsResponse;
import com.upgrad.quora.service.business.QuestionBusinessService;
import com.upgrad.quora.service.entity.QuestionEntity;
import com.upgrad.quora.service.exception.AuthorizationFailedException;
import com.upgrad.quora.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;


//This Controller class deals with all the request related to question.

@RestController
@RequestMapping("/")
public class QuestionController {


    @Autowired
    private QuestionBusinessService questionBusinessService;


    //This method gets all the question stored in the data base.
    // There is only requirement that the user has to be signed in user with valid accesstoken.
    // This method returns a List of questionDetailsResponse as there would be no of questions stored in the database.
    //All the exception arising and as required has been handled.

    @RequestMapping(method = RequestMethod.GET,path = "/question/all",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<QuestionDetailsResponse>> getAllQuestions(@RequestHeader("authorization") final String authorization) throws AuthorizationFailedException {

        List<QuestionEntity> questionEntities = questionBusinessService.getAllQuestions(authorization);

        List<QuestionDetailsResponse> questionDetailsResponseList = new LinkedList<>();//list is created to return.

        //This loop iterates through the list and the question uuid and content to the questionDetailResponse.
        //This is later added to the questionDetailsResponseList to return to the client.
        for(QuestionEntity questionEntity:questionEntities){
            QuestionDetailsResponse questionDetailsResponse = new QuestionDetailsResponse().id(questionEntity.getUuid()).content(questionEntity.getContent());
            questionDetailsResponseList.add(questionDetailsResponse);
        }

        return new ResponseEntity<List<QuestionDetailsResponse>>(questionDetailsResponseList, HttpStatus.OK);

    }

    //This method is used to get all the question posted by the user it uses uuid to get user details and then get user question.
    // There is only requirement that the user has to be signed in user with valid accesstoken.
    // This method returns a List of questionDetailsResponse as there would be no of questions stored in the database.
    //All the exception arising and as required has been handled.
    @RequestMapping(method = RequestMethod.GET,path = "question/all/{userId}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<QuestionDetailsResponse>> getAllQuestionsByUser(@PathVariable(value = "userId")final String uuid,@RequestHeader(value = "authorization")final String authorization) throws UserNotFoundException, AuthorizationFailedException {

        List<QuestionEntity> questionEntities = questionBusinessService.getAllQuestionsByUser(uuid,authorization);

        List<QuestionDetailsResponse> questionDetailsResponseList = new LinkedList<>();//list is created to return.

        //This loop iterates through the list and the question uuid and content to the questionDetailResponse.
        //This is later added to the questionDetailsResponseList to return to the client.
        for(QuestionEntity questionEntity:questionEntities){
            QuestionDetailsResponse questionDetailsResponse = new QuestionDetailsResponse().id(questionEntity.getUuid()).content(questionEntity.getContent());
            questionDetailsResponseList.add(questionDetailsResponse);
        }

        return new ResponseEntity<List<QuestionDetailsResponse>>(questionDetailsResponseList, HttpStatus.OK);

    }

}
