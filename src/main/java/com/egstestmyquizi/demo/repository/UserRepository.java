package com.egstestmyquizi.demo.repository;


import com.egstestmyquizi.demo.model.persistence.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByUserName(String userName);


    User findUserByEmail(String email);


    User findByUserNameAndPassword(String userName, String password);





}
