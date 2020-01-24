package com.egstestmyquizi.demo.service.impl;

 ;
 import com.egstestmyquizi.demo.exception.UserRegistrationException;
 import com.egstestmyquizi.demo.model.persistence.User;
 import com.egstestmyquizi.demo.repository.UserRepository;
 import com.egstestmyquizi.demo.service.api.UserService;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public void save(User user) throws UserRegistrationException {
        User findByUserName = findByUserName(user.getUserName());
        if (findByUserName == null) {
            userRepository.save(user);
        }else {
            throw new UserRegistrationException("That username is taken. Try another");
        }

    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User findByUserName(String name) {
        return userRepository.findUserByName(name);
    }

    @Override
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateEmail(String  userName, String email) {
        User user = userRepository.findUserByName(userName);
        user.setEmail(email);
        userRepository.save(user);
    }

    @Override
    public void updateName(String userName, String name) {
        User user = userRepository.findUserByName(userName);
        user.setName(name);
        userRepository.save(user);
    }

    @Override
    public void updateSurName(String userName, String sueName) {
        User user = userRepository.findUserByName(userName);
        user.setSurName(sueName);
        userRepository.save(user);
    }

    @Override
    public void updatePassword(String userName, String password) {
        User user = userRepository.findUserByName(userName);
        user.setPassword(password);
        userRepository.save(user);
    }

    @Override
    public void updateEnable(String userName, boolean isEnable) {
        User user = userRepository.findUserByName(userName);
        user.setIsEnable(isEnable);
        userRepository.save(user);
    }
}
