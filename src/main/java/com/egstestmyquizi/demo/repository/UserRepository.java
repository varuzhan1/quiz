package com.egstestmyquizi.demo.repository;


import com.egstestmyquizi.demo.model.persistence.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserByName(String name);
    User findUserByEmail(String email);


}
