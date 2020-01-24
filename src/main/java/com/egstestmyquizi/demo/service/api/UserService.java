package com.egstestmyquizi.demo.service.api;


import com.egstestmyquizi.demo.model.persistence.User;

import java.util.List;

public interface UserService {

    void save(User user) throws Exception;

    List<User> findAll();

    User findById(Integer id);

    User findByUserName(String userName);

    void deleteUserById(Integer id);

    void updateEmail(String userName, String email);

    void updateName(String userName, String name);

    void updateSurName(String userName, String sueName);

    void updatePassword(String userName, String password);

    void updateEnable(String userName, boolean isEnable);

}
