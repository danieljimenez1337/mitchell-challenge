package com.example.challenge.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleInvalidParameters(MethodArgumentNotValidException exception, WebRequest request) {
        List<String> validationErrors = new ArrayList<>();
        BindingResult result = exception.getBindingResult();
        for (var fieldError : result.getFieldErrors()) {
            validationErrors.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
        }

        return createResponseEntity("Invalid parameters", HttpStatus.BAD_REQUEST, validationErrors);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest request) {
        List<String> errors = new ArrayList<>();
        errors.add(exception.toString());
        return createResponseEntity("Server error", HttpStatus.INTERNAL_SERVER_ERROR, errors);
    }

    private ResponseEntity<Object> createResponseEntity(String summary, HttpStatus status, List<String> errors) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("error_summary", summary);
        body.put("errors", errors);
        body.put("status", status.value());

        return new ResponseEntity<>(body, status);
    }
}
