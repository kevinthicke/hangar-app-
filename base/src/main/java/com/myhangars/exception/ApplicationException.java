package com.myhangars.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter @Setter
public class ApplicationException extends RuntimeException {

    private String errorMessage;
    private HttpStatus httpStatus;

    public ApplicationException(ApplicationExceptionCause applicationExceptionCause) {
        this.errorMessage = applicationExceptionCause.getErrorCode();
        this.httpStatus = applicationExceptionCause.getHttpStatus();
    }

}
