package com.eduardogreff.domain.services.exceptions;

public class BookNotFound extends RuntimeException {
    public BookNotFound(String message) {
        super(message);
    }
}
