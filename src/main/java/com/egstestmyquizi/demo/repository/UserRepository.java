package com.egstestmyquizi.demo.repository;


import com.egstestmyquizi.demo.model.dto.LeaderBoardDto;
import com.egstestmyquizi.demo.model.persistence.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


     User findByEmail(String email);

    List<User> findAllBy(Sort sort);
}
