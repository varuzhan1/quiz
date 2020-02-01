package com.egstestmyquizi.demo.security;

import com.egstestmyquizi.demo.model.persistence.User;
import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user) {
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().name()));
        this.user = user;
    }


    public User getUser() {
        return user;
    }
}
