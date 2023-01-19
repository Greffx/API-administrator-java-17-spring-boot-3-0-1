package com.eduardogreff.domain.services.exceptions;

public class PersonNotFound extends RuntimeException {
    public PersonNotFound(String message) {
        super(message);
    }
}
