package com.myhangars.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public enum ApplicationExceptionCause implements Serializable {

    PRODUCT_NOT_FOUND("Product not found", HttpStatus.NOT_FOUND),
    PRODUCTS_NOT_FOUND("There is not products in DB", HttpStatus.NOT_FOUND),

    HANGAR_NAME_NOT_UNIQUE("Hangar name is not unique", HttpStatus.BAD_REQUEST),
    HANGAR_NOT_FOUND("Hangar not found", HttpStatus.NOT_FOUND),
    HANGARS_NOT_FOUND("There is not hangars in DB", HttpStatus.NOT_FOUND)
    ;

    private String errorCode;
    private HttpStatus httpStatus;

}
