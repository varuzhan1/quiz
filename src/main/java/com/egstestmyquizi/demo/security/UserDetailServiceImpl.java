package com.egstestmyquizi.demo.security;

import com.egstestmyquizi.demo.model.persistence.User;
import com.egstestmyquizi.demo.repository.UserRepository;

import com.egstestmyquizi.demo.util.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with " + username + " username does not exists!");
        }
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return org.springframework.security.core.userdetails.User.withUsername(user.getUserName()).password(user.getPassword())
                .authorities(Collections.emptyList()).build();
    }
}
