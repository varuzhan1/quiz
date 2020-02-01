package com.egstestmyquizi.demo.service.api;

import com.egstestmyquizi.demo.model.persistence.Answer;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AnswerService {

    void saveAll(List<Answer> answers) ;

    List<Answer> findAllByQuestionId(int questionId);

}
