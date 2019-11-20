package com.myhangars.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

public class GenericException {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class NotFound extends RuntimeException {

        public NotFound() {
            super("Items not found");
        }

        public NotFound(String name) {
            super(String.format("There is no element with name %s", name));
        }

        public NotFound(long id) {
            super(String.format("There are no element with id %d", id));
        }
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    public static class AlreadyExists extends RuntimeException {

        public AlreadyExists(String name) {
            super(String.format("Element %s already exists in DB", name));
        }
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public static class CannotSave extends RuntimeException {

        public CannotSave() {
            super("Cannot save element");
        }

        public CannotSave(String name) {
            super(String.format("Cannot save element %s", name));
        }
    }
}
