package com.egstestmyquizi.demo.repository;


import com.egstestmyquizi.demo.model.dto.LeaderBoard;
import com.egstestmyquizi.demo.model.persistence.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


     User findByEmail(String email);

    List<LeaderBoard> findAllBy(Sort sort);

    //User findByUserNameAndPassword(String userName, String password);l
}
