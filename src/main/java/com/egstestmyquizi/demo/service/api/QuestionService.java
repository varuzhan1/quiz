package com.egstestmyquizi.demo.service.api;


import com.egstestmyquizi.demo.model.persistence.Answer;
import com.egstestmyquizi.demo.model.persistence.Question;
import org.springframework.data.domain.Page;

import java.util.List;

public interface QuestionService {

    Question saveQuestion(Question question);

    void saveWithAnswer(Question question, List<Answer> answers);


    List<Question> findAll();

    Question findById(Integer id);

    List<Question> findByCategory(String category);

    Page<Question> findByPage(Integer page, Integer size);

    void updateQuestionById(Integer id, String text);

    void deleteQuestionById(Integer id);

}
