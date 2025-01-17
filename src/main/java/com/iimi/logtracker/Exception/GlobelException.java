package com.iimi.logtracker.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobelException extends RuntimeException {
    @ExceptionHandler(value = NotFound.class)
    public ResponseEntity<String> AlreadyExist(NotFound notFound){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound.getMessage());
    }

}
