package com.egstestmyquizi.demo.exception;

public class UnAuthorizedException extends Exception {

    public UnAuthorizedException() {
        super();
    }

    public UnAuthorizedException(String message) {
        super(message);
    }
}
