package com.group6.webbportal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Error> exceptionHandler(EntityNotFoundException entityNotFoundException) {
        /*    SpringBootRestJpaApplication.logger.error(studentNotFoundException.getMessage(), new StudentNotFoundException());*/
        Error error = new Error();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(entityNotFoundException.getMessage());
        error.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Error> exceptionHandler(Exception exception) {
        /*SpringBootRestJpaApplication.logger.error(exception.getMessage(), new Exception());*/
        Error error = new Error();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exception.getMessage());
        error.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
