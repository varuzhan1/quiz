package com.egstestmyquizi.demo.controller;


import com.egstestmyquizi.demo.model.business.Test;
import com.egstestmyquizi.demo.model.persistence.Question;
import com.egstestmyquizi.demo.service.api.AnswerService;
import com.egstestmyquizi.demo.service.api.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    AnswerService answerService;


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/question/add")
    public void questionAdd(@RequestBody Test test) {
        questionService.saveWithAnswer(test.getQuestion(), test.getAnswers());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/questions")
    public List<Question> findAll() {
        return questionService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("question/update")
    public void updateQuestionById(@RequestParam("id") Integer id, @RequestParam("text") String text) {
        questionService.updateQuestionById(id, text);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("question/delete")
    public void deleteQuestionById(@RequestParam("id") Integer id) {
        questionService.deleteQuestionById(id);
    }

    @GetMapping("/question/{id}")
    public Question findAllByQuestion(@PathVariable("id") Integer id) {
        return questionService.findById(id);
    }

    @GetMapping("/question/category")
    public List<Question> getQuestionByCategory(@RequestParam("category") String category) {
        return questionService.findByCategory(category);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/question/page")
    public Page<Question> questionFindByPage(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return questionService.findByPage(page, size);
    }
}