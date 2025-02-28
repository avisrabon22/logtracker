package com.iimi.logtracker.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobelException extends Exception {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> Exception(Exception exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }

    @ExceptionHandler(value = NotFound.class)
    public ResponseEntity<?> NotFound(NotFound notFound){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound.getMessage());
    }

    @ExceptionHandler(value = AlreadyExist.class)
    public ResponseEntity<?> AlreadyExist(AlreadyExist alreadyExist){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(alreadyExist.getMessage());
    }

    @ExceptionHandler(value = EmptyException.class)
    public  ResponseEntity<?> EmptyException(EmptyException emptyException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(emptyException.getMessage());

    }
}
