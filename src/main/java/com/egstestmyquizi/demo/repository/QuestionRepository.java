package com.egstestmyquizi.demo.repository;


import com.egstestmyquizi.demo.model.persistence.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Integer> {

    List<Question> findAllByCategory(String category);
}
