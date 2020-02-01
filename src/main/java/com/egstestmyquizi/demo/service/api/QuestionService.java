package com.egstestmyquizi.demo.service.api;


import com.egstestmyquizi.demo.model.persistence.Answer;
import com.egstestmyquizi.demo.model.persistence.Question;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface QuestionService {

    Question saveQuestion(Question question);

    List<Question> findAll();

    Question findById(Integer id);

    Page<Question> findByPage(Integer page, Integer size);

    public void saveWithAnswer(Question question, List<Answer> answers);

    void updateById(Integer id, String text);

    void deleteById(Integer id);

}
