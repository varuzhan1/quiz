package com.egstestmyquizi.demo.repository;

import com.egstestmyquizi.demo.model.persistence.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {

    Quiz save(Quiz quiz);
}
