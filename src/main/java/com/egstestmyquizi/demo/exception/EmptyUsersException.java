package com.egstestmyquizi.demo.exception;

public class EmptyUsersException extends Exception {
    public EmptyUsersException() {
        super();
    }

    public EmptyUsersException(String message) {
        super(message);
    }
}
