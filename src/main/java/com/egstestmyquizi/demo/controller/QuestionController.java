package com.egstestmyquizi.demo.controller;


import com.egstestmyquizi.demo.model.persistence.Question;
import com.egstestmyquizi.demo.repository.QuestionRepository;
import com.egstestmyquizi.demo.service.api.AnswerService;
import com.egstestmyquizi.demo.service.api.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    AnswerService answerService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/question/add")
    public void questionAdd(@RequestBody Question question) {
        questionService.saveWithAnswer(question, question.getAnswers());
    }

    @GetMapping("/answers")
    public Object getQuestion(){
    return     questionService.findAll();
    }

//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/questions")
//    public List<Quiz> findAllQuestion() {
//
//        List<Question> allQuestions =  questionService.findAll();
//        List<Answer> answerList;
//        List<Quiz> quizList = new ArrayList<>();
//
//        for (Question question :allQuestions
//                ) {
//            int id = question.getId();
//            answerList = answerService.findAllByQuestionId(id);
//            Quiz quiz = new Quiz(question, answerList);
//            quizList.add(quiz);
//        }
//        return quizList;
//    }
//
//    @ResponseStatus(HttpStatus.OK)
//    @PutMapping("/question/update")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public void updateQuestionById(@RequestParam("id") Integer id, @RequestParam("text") String text) {
//        questionService.updateQuestionById(id, text);
//    }
//
//    @ResponseStatus(HttpStatus.OK)
//    @DeleteMapping("/question/delete")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public void deleteQuestionById(@RequestParam("id") Integer id) {
//        questionService.deleteQuestionById(id);
//    }
//
//    @GetMapping("/question/{id}")
//    public Question findById(@PathVariable("id") Integer id) {
//        return questionService.findById(id);
//    }
//
//
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/question/page")
//    public Page<Question> questionFindByPage(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
//        return questionService.findByPage(page, size);
//    }
//
//    @ResponseStatus(HttpStatus.OK)
//    @PutMapping("/answer/add")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public  void addAnswer(@RequestBody Answer answer){
//      answerService.addAnswer(answer);
//    }
}