package com.example.springsecuritydemo.security.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity handleException(ResponseStatusException e) {
    int i = 0;
    return ResponseEntity
        .status(e.getStatus())
        .body(new ErrorDTO(e.getMessage()));
  }
}
