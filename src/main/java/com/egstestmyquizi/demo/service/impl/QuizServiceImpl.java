package com.egstestmyquizi.demo.service.impl;

import com.egstestmyquizi.demo.exception.QuizNotFoundException;
import com.egstestmyquizi.demo.model.persistence.Question;
import com.egstestmyquizi.demo.model.persistence.Quiz;
import com.egstestmyquizi.demo.repository.QuestionRepository;
import com.egstestmyquizi.demo.repository.QuizRepository;
import com.egstestmyquizi.demo.service.api.QuestionService;
import com.egstestmyquizi.demo.service.api.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuestionService questionService;

    @Override
    @Transactional
    public void save(Quiz quiz) {
        Quiz quizWithId = quizRepository.save(quiz);
        List<Question> questions = quizWithId.getQuestions();
        for (int i = 0; i < questions.size(); i++) {
            questionService.saveQuestion(questions.get(i));
        }

    }
    @Override
    @Transactional
    public void saveAfterDeleteQuestion(Quiz quiz) {
    quizRepository.save(quiz);

    }

    @Override
    @Transactional
    public Optional<Quiz> findById(Integer id) throws QuizNotFoundException {
        Optional<Quiz> quiz = quizRepository.findById(id);
        if (!quiz.isPresent()) {
            throw new QuizNotFoundException("Quiz not found");
        }
        return quiz;
    }

    @Override
    public List<Quiz> findAll() throws QuizNotFoundException {
        List<Quiz> quizzes = quizRepository.findAll();
        if (quizzes.isEmpty()) {
            throw new QuizNotFoundException("Quizzes are not found");
        }
        return quizzes;
    }

    @Override
    @Transactional
    public List<Question> getQuestionsByCount(Integer quizId, Integer countOfQuestions) throws QuizNotFoundException {
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        if (!quiz.isPresent()) {
            throw new QuizNotFoundException("Quiz not found");
        }

        return quiz.get().getQuestions().subList(0, countOfQuestions);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) throws QuizNotFoundException {
        Optional<Quiz> quiz = quizRepository.findById(id);
        if (!quiz.isPresent()) {
            throw new QuizNotFoundException("Quiz not found");
        }
        quizRepository.delete(quiz.get());
        for (int i = 0; i < quiz.get().getQuestions().size(); i++) {
            questionService.deleteById(quiz.get().getQuestions().get(i).getId());
        }
    }

    @Override
    public void addQuestionByQuizId(Integer quizId, Question question) {
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        List<Question> questions = quiz.get().getQuestions();
        questions.add(question);
        questionService.saveQuestion(question);

    }
}
