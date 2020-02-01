package com.egstestmyquizi.demo.service.api;


import com.egstestmyquizi.demo.exception.EmptyUsersException;
import com.egstestmyquizi.demo.exception.UserNotFoundException;
import com.egstestmyquizi.demo.model.dto.LeaderBoard;
import com.egstestmyquizi.demo.model.persistence.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {

    void save(User user) throws Exception;

    List<User> findAll() throws EmptyUsersException;

    Optional<User> findById(Integer id) throws UserNotFoundException;

    User findByEmail(String email) throws UserNotFoundException;

    void deleteById(Integer id) throws UserNotFoundException;

    void updateEmail(Integer id, String newEmail) throws UserNotFoundException;


    void updateSurName(Integer id, String surName) throws UserNotFoundException;

    void updatePassword(Integer id, String newPassword) throws UserNotFoundException;


    boolean checkUserByIdAndPassword(Integer id, String password);

    String loginByEmailAndPassword(String email, String password);

    List<LeaderBoard> leaderBoard();
}
