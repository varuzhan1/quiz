package com.egstestmyquizi.demo.controller;


import com.egstestmyquizi.demo.exception.UserRegistrationException;
import com.egstestmyquizi.demo.model.persistence.User;
import com.egstestmyquizi.demo.security.CurrentUser;
import com.egstestmyquizi.demo.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;



    @PostMapping("/register")
    public HttpStatus register(@RequestBody User user)  {
         user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            userService.save(user);
        } catch (UserRegistrationException e) {
          return  HttpStatus.NON_AUTHORITATIVE_INFORMATION;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return HttpStatus.OK;
    }
    @PostMapping("/register/admin")
    public HttpStatus registerAdmin(@RequestBody User user)  {
         user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            userService.save(user);
        } catch (UserRegistrationException e) {
          return  HttpStatus.NON_AUTHORITATIVE_INFORMATION;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return HttpStatus.OK;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users")
    public List<User> findAllUser() {
        return userService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/{id}")
    public User findByIdUser(@PathVariable("id") Integer id) {
        return userService.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/user/delete/{id}")
    public void deleteUserById(@PathVariable("id") Integer id) {
        userService.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("user/update/email")
    public void updateEmail(@AuthenticationPrincipal CurrentUser currentUser, @RequestParam("email") String email) {
        userService.updateEmail(currentUser.getUsername(), email);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("user/update/name")
    public void updateName(@AuthenticationPrincipal CurrentUser currentUser, @RequestParam("name") String name) {
        userService.updateName(currentUser.getUsername(),name);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("user/update/surname")
    public void updateSurName(@AuthenticationPrincipal CurrentUser currentUser, @RequestParam("surname") String surname) {
        userService.updateSurName(currentUser.getUsername(), surname);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("user/update/password")
    public void updatePassword(@AuthenticationPrincipal CurrentUser currentUser, @RequestParam("password") String password) {
        userService.updatePassword(currentUser.getUsername(), password);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("user/update/enable")
    public void updateEnable(@RequestParam("username") String userName, @RequestParam("enable") boolean isEnable) {
        userService.updateEnable(userName, isEnable);
    }


}
