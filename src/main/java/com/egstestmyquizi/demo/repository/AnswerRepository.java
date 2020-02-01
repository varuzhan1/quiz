package com.egstestmyquizi.demo.repository;


import com.egstestmyquizi.demo.model.persistence.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    List<Answer> findAllByQuestionId(int questionId);
}
