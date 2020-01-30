package com.egstestmyquizi.demo.service.impl;



import com.egstestmyquizi.demo.model.persistence.Answer;
import com.egstestmyquizi.demo.repository.AnswerRepository;
import com.egstestmyquizi.demo.service.api.AnswerService;
import com.egstestmyquizi.demo.service.api.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    QuestionService questionService;

    @Override
    public void saveAllAnswers(List<Answer> answers) {
        answerRepository.saveAll(answers);
    }

    @Override
    public void addAnswer(Answer answer) {
         answerRepository.save(answer);
    }


}
