package com.myhangars.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ArgumentException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class IdNotAllowed extends RuntimeException {
        public IdNotAllowed(long id) {
            super(String.format("Id %d must be greater than zero", id));
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class BodyCannotBeNull extends RuntimeException {
        public BodyCannotBeNull() {
            super("Body should not be null");
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class PriceMustBePositive extends RuntimeException {
        public PriceMustBePositive() {
            super("Price should be positive");
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class UsernameAlreadyExists extends RuntimeException {
        public UsernameAlreadyExists(String username) {
            super(String.format("Username %s already exists", username));
        }
    }
}
