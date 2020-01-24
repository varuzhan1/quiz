package com.egstestmyquizi.demo.service.api;

import com.egstestmyquizi.demo.model.persistence.Answer;

import java.util.List;

public interface AnswerService {

    void saveAllAnswers(List<Answer> answers) ;

}
