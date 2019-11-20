package com.myhangars.exception;

import com.myhangars.error.ApiError;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    protected ResponseEntity<?> handleApplicationException(ApplicationException applicationException) {
        ApiError apiError = new ApiError();

        apiError.setMessage(applicationException.getErrorMessage());
        apiError.setStatus(applicationException.getHttpStatus());

        return new ResponseEntity<ApiError>(
                apiError,
                apiError.getStatus());
    }

    /*
    @Validate For Validating Path Variables and Request Parameters

    TODO: Ver esto https://reflectoring.io/bean-validation-with-spring-boot/
    */
    @ExceptionHandler(ConstraintViolationException.class)
    public void constraintViolationException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

    /*
    For @valid validation errors
     */

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException,
                                                             HttpHeaders httpHeaders,
                                                             HttpStatus httpStatus,
                                                             WebRequest webRequest) {
        ApiError apiError = new ApiError();

        List<String> errMessages = methodArgumentNotValidException
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        String customMessage = errMessages.get(errMessages.size() - 1);

        apiError.setMessage(customMessage);
        apiError.setStatus(HttpStatus.BAD_REQUEST);

        /*
        TODO: Crear una clase ArgumentError que tenga los siguientes atributos:

        Email: methodArgumentNotValidException.getBindingResult().getFieldError();
        UserProfileDto: methodArgumentNotValidException.getBindingResult().getObjectName();

         */

        return new ResponseEntity<Object>(
                apiError,
                apiError.getStatus()
        );
    }

}
