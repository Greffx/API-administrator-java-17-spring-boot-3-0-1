package com.eduardogreff.api.controllers.exception;

import com.eduardogreff.api.config.StandardErrorConfig;
import com.eduardogreff.domain.services.exceptions.PersonNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;

@RestControllerAdvice
public class PersonExceptionHandlerController {

    @ExceptionHandler(PersonNotFound.class)
    public ResponseEntity<StandardErrorConfig> personNotFoundExceptionHandler(WebRequest request, PersonNotFound exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardErrorConfig(Instant.now(), HttpStatus.NOT_FOUND.value(), "Client error", request.getDescription(false), exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StandardErrorConfig> badRequestExceptionHandler(WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StandardErrorConfig(Instant.now(), HttpStatus.BAD_REQUEST.value(), "Client error", request.getDescription(false), "Invalid value, try another one"));
    }
}
