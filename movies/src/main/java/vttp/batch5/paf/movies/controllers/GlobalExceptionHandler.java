package vttp.batch5.paf.movies.controllers;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vttp.batch5.paf.movies.models.ErrorMessage;
import vttp.batch5.paf.movies.models.InsertionErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InsertionErrorException.class)
    public ResponseEntity<ErrorMessage> handleInsertionErrorException(InsertionErrorException ex,  HttpServletRequest request, HttpServletResponse response){
        ErrorMessage message = new ErrorMessage(ex.getIds(), ex.getMessage(),new Date()); 

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_IMPLEMENTED);
    }
    
}
