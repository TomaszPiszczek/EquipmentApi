package com.example.EquipmentApi.exceptions;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;


@ControllerAdvice
public class GlobalExceptionHandler {
    //todo exception handler for illegalStateEx
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

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handlePSQLExceptions(SQLException ex) {
        ExceptionResponse response = new ExceptionResponse();

        String errorMessage = "Duplicate key violation: " + ex.getMessage();

        response.setErrorCode("BAD_REQUEST");
        response.setErrorMessage(errorMessage);

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("NOT_FOUND");
        response.setErrorMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}

