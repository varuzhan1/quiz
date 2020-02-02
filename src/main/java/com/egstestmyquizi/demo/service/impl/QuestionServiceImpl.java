package com.egstestmyquizi.demo.service.impl;


import com.egstestmyquizi.demo.exception.QuizNotFoundException;
import com.egstestmyquizi.demo.model.persistence.Answer;
import com.egstestmyquizi.demo.model.persistence.Question;
import com.egstestmyquizi.demo.model.persistence.Quiz;
import com.egstestmyquizi.demo.repository.QuestionRepository;
import com.egstestmyquizi.demo.service.api.AnswerService;
import com.egstestmyquizi.demo.service.api.QuestionService;
import com.egstestmyquizi.demo.service.api.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerService answerService;

    @Autowired
    QuizService quizService;

    @Override
    @Transactional
    public Question saveQuestion(Question question) {
        Question questionWithId = questionRepository.save(question);
        List<Answer> answers = question.getAnswers();
        answerService.saveAll(answers);
        return questionWithId;
    }

//    @Override
//    @Transactional
//    public void saveWithAnswer(Question question, List<Answer> answers) {
//        Question questionWithId = saveQuestion(question);
//        answers = questionWithId.getAnswers();
//        //answers.forEach(item -> item.setQuestion(questionWithId));
//        answerService.saveAll(answers);
//    }

    @Override
    @Transactional
    public List<Question> findAll() {

        return questionRepository.findAll();
    }

    @Override
    @Transactional
    public Question findById(Integer id) {
        return questionRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Page<Question> findByPage(Integer page, Integer size) {
        return questionRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    @Transactional
    public void updateById(Integer id, Question newQuestion) {
        Question question = questionRepository.findById(id).get();

        if(newQuestion.getQuestion() != null) {
            question.setQuestion(newQuestion.getQuestion());
        }
        if(newQuestion.getPoint() != null) {
            question.setPoint(newQuestion.getPoint());
        }
        if(newQuestion.getCorrectAnswer() != null) {
            question.setCorrectAnswer(newQuestion.getCorrectAnswer());
        }
        questionRepository.save(question);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Optional<Question> question = questionRepository.findById(id);
        questionRepository.deleteById(id);
        for (int i = 0; i < question.get().getAnswers().size(); i++){
            answerService.deleteById(question.get().getAnswers().get(i).getId());
        }
    }


    @Override
    @Transactional
    public void deleteByQuizId(Integer quizId, Integer questionId) throws QuizNotFoundException {
        Optional<Quiz> quiz = quizService.findById(quizId);
        Optional<Question> question = questionRepository.findById(questionId);
        quiz.get().getQuestions().remove(question.get());
        quizService.saveAfterDeleteQuestion(quiz.get());
        deleteById(questionId);
    }
}
