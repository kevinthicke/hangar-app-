package com.myhangars.products_hangar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class GenericException {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class NotFound extends RuntimeException {
        public NotFound(long hangarId, long productId) {
            super(String.format("Product_Hangar with hangar_id  = %d and product_id = %d not found", hangarId, productId));
        }
    }
}
