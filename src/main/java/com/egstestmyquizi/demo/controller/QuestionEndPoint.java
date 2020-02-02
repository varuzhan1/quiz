package com.egstestmyquizi.demo.controller;

import com.egstestmyquizi.demo.exception.QuizNotFoundException;
import com.egstestmyquizi.demo.model.persistence.Question;
import com.egstestmyquizi.demo.service.api.AnswerService;
import com.egstestmyquizi.demo.service.api.QuestionService;
import com.egstestmyquizi.demo.service.api.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class QuestionEndPoint {

    @Autowired
    QuizService quizService;

    @Autowired
    QuestionService questionService;

    @Autowired
    AnswerService answerService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/add/{quiz_id}")
    public void questionAdd(@PathVariable("quiz_id") Integer quizId, @RequestBody Question question) throws QuizNotFoundException {
        quizService.addQuestionByQuizId(quizId, question);

    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update/{id}")
    public void updateQuestionById(@PathVariable("id") Integer id, @RequestBody Question newQuestion) {
        questionService.updateById(id, newQuestion);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete")
    public void deleteQuestionById(@RequestParam("quiz_id") Integer quizId, @RequestParam("question_id") Integer questionId) throws QuizNotFoundException {
        questionService.deleteByQuizId(quizId, questionId);
    }

    @GetMapping("/{id}")
    public Question findById(@PathVariable("id") Integer id) {
        return questionService.findById(id);
    }

}