package com.egstestmyquizi.demo.controller;

import com.egstestmyquizi.demo.exception.QuizNotFoundException;
import com.egstestmyquizi.demo.model.dto.QuizDto;
import com.egstestmyquizi.demo.model.persistence.Quiz;
import com.egstestmyquizi.demo.service.api.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/quiz")
public class QuizEndPoint {

    @Autowired
    QuizService quizService;


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public void add(@RequestBody Quiz quiz) {
        quizService.save(quiz);
    }

    @GetMapping("/quizzes")
    @ResponseStatus(HttpStatus.OK)
    public List<QuizDto> findAll() throws QuizNotFoundException {
        return quizService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public QuizDto findById(@PathVariable("id") Integer id) throws QuizNotFoundException {
        return quizService.findById(id);
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id) throws QuizNotFoundException {
        quizService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateName(@PathVariable("id") Integer id, @RequestBody Quiz newQuiz) throws QuizNotFoundException {
        Optional<Quiz> quiz = quizService.findQuizById(id);
        quiz.get().setName(newQuiz.getName());
        quizService.save(quiz.get());
    }
}
