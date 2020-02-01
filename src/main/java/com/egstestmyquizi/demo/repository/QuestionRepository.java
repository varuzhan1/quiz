package com.egstestmyquizi.demo.repository;


import com.egstestmyquizi.demo.model.persistence.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {
}
