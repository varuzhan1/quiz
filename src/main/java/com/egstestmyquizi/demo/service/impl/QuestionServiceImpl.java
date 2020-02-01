package com.egstestmyquizi.demo.service.impl;


import com.egstestmyquizi.demo.model.persistence.Answer;
import com.egstestmyquizi.demo.model.persistence.Question;
import com.egstestmyquizi.demo.repository.QuestionRepository;
import com.egstestmyquizi.demo.service.api.AnswerService;
import com.egstestmyquizi.demo.service.api.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerService answerService;

    @Override
    @Transactional
    public Question saveQuestion(Question question) {
        Question questionWithId = questionRepository.save(question);
        return questionWithId;
    }

    @Override
    @Transactional
    public void saveWithAnswer(Question question, List<Answer> answers) {
        Question questionWithId = saveQuestion(question);
        answers.forEach(item -> item.setQuestion(questionWithId));
        answerService.saveAll(answers);
    }

    @Override
    @Transactional
    public List<Question> findAll() {

        return questionRepository.findAll();
    }

    @Override
    @Transactional
    public Question findById(Integer id) {
        return questionRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Page<Question> findByPage(Integer page, Integer size) {
        return questionRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    @Transactional
    public void updateById(Integer id, String text) {
        Question question = questionRepository.findById(id).get();
        question.setText(text);
        questionRepository.save(question);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        questionRepository.deleteById(id);
    }
}
