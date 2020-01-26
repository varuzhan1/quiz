package com.egstestmyquizi.demo.controller;


import com.egstestmyquizi.demo.exception.UserRegistrationException;
import com.egstestmyquizi.demo.model.persistence.User;
import com.egstestmyquizi.demo.service.api.UserService;
import com.egstestmyquizi.demo.util.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Map<String, String> loginAdmin(@RequestBody Map<String, String> values) {
        Map<String, String> accessToken = new HashMap<>();
        accessToken.put("access_token", userService.loginByUserNameAndPassword(
                values.get("userName"), values.get("password")));
        return accessToken;
    }


    @PostMapping("/register")
    public HttpStatus register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            userService.save(user);
        } catch (UserRegistrationException e) {
            return HttpStatus.NON_AUTHORITATIVE_INFORMATION;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return HttpStatus.OK;
    }

    @PostMapping("/register/admin")
    public HttpStatus registerAdmin(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            userService.save(user);
        } catch (UserRegistrationException e) {
            return HttpStatus.NON_AUTHORITATIVE_INFORMATION;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return HttpStatus.OK;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> findAllUser() {
        return userService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User findByIdUser(@PathVariable("id") Integer id) {
        return userService.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/user/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteUserById(@PathVariable("id") Integer id) {
        userService.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/user/update/email")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void updateEmail(@RequestHeader("token") String token, @RequestParam("email") String email) {
        String[] tokenParsValues = Encrypt.decodeAccount(token);
        userService.updateEmail(tokenParsValues[0], email);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/user/update/name")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void updateName(@RequestHeader("token") String token, @RequestParam("name") String name) {
        String[] tokenParsValues = Encrypt.decodeAccount(token);
        userService.updateName(tokenParsValues[0], name);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/user/update/surname")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void updateSurName(@RequestHeader("token") String token,@RequestParam("surname") String surname) {
        String[] tokenParsValues = Encrypt.decodeAccount(token);
        userService.updateSurName(tokenParsValues[0], surname);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/user/update/password")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void updatePassword(@RequestHeader("token") String token,@RequestParam("password") String password) {
        String[] tokenParsValues = Encrypt.decodeAccount(token);
        userService.updatePassword(tokenParsValues[0], password);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/user/update/enable")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateEnable(@RequestHeader("token") String token, @RequestParam("enable") boolean isEnable) {
        String[] tokenParsValues = Encrypt.decodeAccount(token);
        userService.updateEnable(tokenParsValues[0], isEnable);
    }


}
