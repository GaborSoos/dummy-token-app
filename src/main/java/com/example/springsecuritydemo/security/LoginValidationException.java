package com.example.springsecuritydemo.security;

public class LoginValidationException extends RuntimeException {
    public final int HTTP_STATUS_CODE;
    public LoginValidationException(String message, int httpStatusCode) {
        super(message);
        HTTP_STATUS_CODE = httpStatusCode;
    }


}
