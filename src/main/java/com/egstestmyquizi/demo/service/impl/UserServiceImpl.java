package com.egstestmyquizi.demo.service.impl;

import com.egstestmyquizi.demo.exception.EmptyUsersException;
import com.egstestmyquizi.demo.exception.UserNotFoundException;
import com.egstestmyquizi.demo.exception.UserRegistrationException;

import com.egstestmyquizi.demo.model.dto.LeaderBoard;
import com.egstestmyquizi.demo.model.persistence.User;
import com.egstestmyquizi.demo.repository.UserRepository;
import com.egstestmyquizi.demo.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public void save(User newUser) throws Exception {
        User user = userRepository.findByEmail(newUser.getEmail()).get();
        if (user == null && newUser.getPassword() != null && newUser.getName() != null && newUser.getSurName() != null) {
            userRepository.save(newUser);
        } else {
            throw new UserRegistrationException("User already created or missing some fields. Try again");
        }
    }

    @Override
    @Transactional
    public List<User> findAll() throws EmptyUsersException {
       List<User> users = userRepository.findAll();
       if (users.isEmpty()){
           throw new EmptyUsersException("There is no users!");
       }
       return users;
    }

    @Override
    @Transactional
    public Optional<User> findById(Integer id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("User not found");
        }
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public User findByEmail(String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email).get();
        if(user == null){
            throw new UserNotFoundException("User not found");
        }
        return user;
    }

    @Override
    @Transactional
    public void deleteById(Integer id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateEmail(Integer id, String newEmail) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("User not found");
        }
        user.get().setEmail(newEmail);
    }

    @Override
    @Transactional
    public void updateSurName(Integer id, String surName) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("User not found");
        }
        user.get().setSurName(surName);
    }

    @Override
    public void updatePassword(Integer id, String newPassword) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("User not found");
        }
        user.get().setPassword(newPassword);
    }

    @Override
    public boolean checkUserByIdAndPassword(Integer id, String password) {
        return false;
    }

    @Override
    public String loginByEmailAndPassword(String email, String password) {
        return null;
    }

    @Override
    public List<LeaderBoard> leaderBoard() {
        List<LeaderBoard> leaderBoard = userRepository.findAllBy(Sort.by(Sort.Direction.DESC, "points"));
        return leaderBoard;
    }

//    @Override
//    public String loginByEmailAndPassword(String email, String password) {
//        if (checkUserByUserNameAndPassword(email, password)) {
//            return null;
//        }
//        throw new UnAuthorizedException("login or password is not correct");
//    }


//    @Override
//    public boolean checkUserByUserNameAndPassword(String userName, String password) {
//        User user = userRepository.findUserByUserName(userName);
//        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
//            return true;
//        }
//        return false;
//    }
}
