package com.egstestmyquizi.demo.service.impl;

import com.egstestmyquizi.demo.model.persistence.Answer;
import com.egstestmyquizi.demo.repository.AnswerRepository;
import com.egstestmyquizi.demo.service.api.AnswerService;
import com.egstestmyquizi.demo.service.api.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    QuestionService questionService;

    @Override
    @Transactional
    public void saveAll(List<Answer> answers) {
        answerRepository.saveAll(answers);
    }

    @Override
    @Transactional
    public List<Answer> findAllByQuestionId(int questionId) {
        return answerRepository.findAllByQuestionId(questionId);
    }
}
