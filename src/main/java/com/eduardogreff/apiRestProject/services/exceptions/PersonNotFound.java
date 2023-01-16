package com.eduardogreff.apiRestProject.services.exceptions;

public class PersonNotFound extends RuntimeException {
    public PersonNotFound(String message) {
        super(message);
    }
}
