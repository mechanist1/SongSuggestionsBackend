package org.example.apitestingwitherrorthrowing.Config;


import org.example.apitestingwitherrorthrowing.Dtos.ErrorMessage;
import org.example.apitestingwitherrorthrowing.Exceptions.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorMessage> handleGlobalException(final BusinessException ex) {
        return ResponseEntity.badRequest().body(new ErrorMessage("business error", ex.getMessage()));
    }




}
