package com.egstestmyquizi.demo.controller;


import com.egstestmyquizi.demo.exception.UnAuthorizedException;
import com.egstestmyquizi.demo.exception.UserRegistrationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class BaseController {

    @ExceptionHandler(UserRegistrationException.class)
    public ResponseEntity<String> handleUnAuthorizedException(UnAuthorizedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }


}
