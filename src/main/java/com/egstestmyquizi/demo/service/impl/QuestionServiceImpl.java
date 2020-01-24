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

import javax.transaction.Transactional;
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
        Question question1 = questionRepository.save(question);
        return question1;
    }

    @Override
    @Transactional
    public void saveWithAnswer(Question question, List<Answer> answers) {
        Question questionWithId = saveQuestion(question);
        answers.forEach(item -> item.setQuestion(questionWithId));
        answerService.saveAllAnswers(answers);
    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Question findById(Integer id) {
        return questionRepository.findById(id).get();
    }

    @Override
    public Page<Question> findByPage(Integer page, Integer size) {
        return questionRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public void updateQuestionById(Integer id, String text) {
        Question question = questionRepository.findById(id).get();
        question.setQuestion(text);
        questionRepository.save(question);
    }

    @Override
    public void deleteQuestionById(Integer id) {
        questionRepository.deleteById(id);
    }

    @Override
    public List<Question> findByCategory(String category) {
        return questionRepository.findAllByCategory(category);
    }
}
