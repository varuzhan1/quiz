package com.egstestmyquizi.demo.service.api;

import com.egstestmyquizi.demo.exception.QuizNotFoundException;
import com.egstestmyquizi.demo.model.persistence.Question;
import com.egstestmyquizi.demo.model.persistence.Quiz;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface QuizService {
    void save(Quiz quiz);
    Optional<Quiz> findById(Integer id) throws QuizNotFoundException;
    List<Quiz> findAll() throws QuizNotFoundException;
    List<Question> getQuestionsByCount(Integer quizId, Integer countOfQuestions) throws QuizNotFoundException;

    void deleteById(Integer id) throws QuizNotFoundException;

}
