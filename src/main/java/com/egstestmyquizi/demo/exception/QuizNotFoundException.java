package com.egstestmyquizi.demo.exception;

public class QuizNotFoundException extends Exception {
    public QuizNotFoundException() {
        super();
    }

    public QuizNotFoundException(String message) {
        super(message);
    }
}
