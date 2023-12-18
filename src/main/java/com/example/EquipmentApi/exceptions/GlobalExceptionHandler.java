package com.example.EquipmentApi.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(ConstraintViolationException ex) {
        ExceptionResponse response = new ExceptionResponse();
        StringBuilder errorMessage = new StringBuilder();

        ex.getConstraintViolations().forEach((violation) -> {
            String fieldName = violation.getPropertyPath().toString();
            String violationMessage = violation.getMessage();
            errorMessage.append(fieldName).append(": ").append(violationMessage).append("; ");
        });

        response.setErrorCode("BAD_REQUEST");
        response.setErrorMessage(errorMessage.toString());

        return ResponseEntity.badRequest().body(response);
    }
}

