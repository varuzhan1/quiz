package com.egstestmyquizi.demo.service.impl;

;
import com.egstestmyquizi.demo.exception.UnAuthorizedException;
import com.egstestmyquizi.demo.exception.UserRegistrationException;
import com.egstestmyquizi.demo.model.persistence.User;
import com.egstestmyquizi.demo.repository.UserRepository;
import com.egstestmyquizi.demo.service.api.UserService;
import com.egstestmyquizi.demo.util.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public String loginByUserNameAndPassword(String userName, String password) {
        if (checkUserByUserNameAndPassword(userName, password)) {
            String token = Encrypt.encodeAccount(userName,password);
            updateUserToken(userName, token);
            return token;
        }
        throw new UnAuthorizedException("login or password is not correct");
    }

    @Override
    public void updateUserToken(String userName, String token) {
        User user = userRepository.findUserByUserName(userName);
        user.setToken(token);
        userRepository.save(user);
    }


    @Override
    public void save(User user) throws UserRegistrationException {
        User findByUserName = findByUserName(user.getUserName());
        if (findByUserName == null) {
            userRepository.save(user);
        } else {
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
        return userRepository.findUserByUserName(name);
    }

    @Override
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateEmail(String userName, String email) {
        User user = userRepository.findUserByUserName(userName);
        user.setEmail(email);
        userRepository.save(user);
    }

    @Override
    public void updateName(String userName, String name) {
        User user = userRepository.findUserByUserName(userName);
        user.setName(name);
        userRepository.save(user);
    }

    @Override
    public void updateSurName(String userName, String sueName) {
        User user = userRepository.findUserByUserName(userName);
        user.setSurName(sueName);
        userRepository.save(user);
    }

    @Override
    public void updatePassword(String userName, String newPassword) {
        User user = userRepository.findUserByUserName(userName);
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    @Override
    public void updateEnable(String userName, boolean isEnable) {
        User user = userRepository.findUserByUserName(userName);
        user.setIsEnable(isEnable);
        userRepository.save(user);
    }

    @Override
    public boolean checkUserByUserNameAndPassword(String userName, String password) {
        User user = userRepository.findUserByUserName(userName);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return true;
        }
        return false;
    }
}
