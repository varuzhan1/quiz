package com.egstestmyquizi.demo.repository;


import com.egstestmyquizi.demo.model.persistence.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
