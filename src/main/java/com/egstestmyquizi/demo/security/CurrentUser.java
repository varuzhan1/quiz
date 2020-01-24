package com.egstestmyquizi.demo.security;


import com.egstestmyquizi.demo.model.persistence.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user) {
        super(user.getUserName(), user.getPassword(),
                user.isEnable(), true,
                true, true,
                Arrays.asList(new SimpleGrantedAuthority(user.getType().name())));

        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
