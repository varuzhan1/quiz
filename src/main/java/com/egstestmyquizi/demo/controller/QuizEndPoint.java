package com.egstestmyquizi.demo.controller;

import com.egstestmyquizi.demo.model.persistence.Quiz;
import com.egstestmyquizi.demo.repository.QuestionRepository;
import com.egstestmyquizi.demo.repository.QuizRepository;
import com.egstestmyquizi.demo.service.api.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quiz")
public class QuizEndPoint {

    @Autowired
    QuizService quizService;


    @PostMapping("/add")
    public void addQuiz(@RequestBody Quiz quiz){
        quizService.save(quiz);
    }
}
