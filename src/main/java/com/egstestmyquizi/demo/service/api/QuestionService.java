package com.egstestmyquizi.demo.service.api;


import com.egstestmyquizi.demo.exception.QuizNotFoundException;
import com.egstestmyquizi.demo.model.dto.QuestionDto;
import com.egstestmyquizi.demo.model.persistence.Answer;
import com.egstestmyquizi.demo.model.persistence.Question;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface QuestionService {

    Question saveQuestion(Question question);

    List<QuestionDto> findAll();

    QuestionDto findById(Integer id);

    Page<Question> findByPage(Integer page, Integer size);

   // public void saveWithAnswer(Question question, List<Answer> answers);

    void updateById(Integer id, Question question);

    void deleteById(Integer id);

    void deleteByQuizId(Integer quizId, Integer questionId) throws QuizNotFoundException;

}
