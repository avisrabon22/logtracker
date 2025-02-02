package com.iimi.logtracker.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobelException extends RuntimeException {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> Exception(Exception exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }

    @ExceptionHandler(value = NotFound.class)
    public ResponseEntity<String> NotFound(NotFound notFound){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound.getMessage());
    }

    @ExceptionHandler(value = AlreadyExist.class)
    public ResponseEntity<String> AlreadyExist(AlreadyExist alreadyExist){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(alreadyExist.getMessage());
    }

}
