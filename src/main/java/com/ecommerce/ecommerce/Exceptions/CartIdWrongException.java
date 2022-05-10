package com.ecommerce.ecommerce.Exceptions;

public class CartIdWrongException extends IllegalArgumentException {
    public CartIdWrongException(String msg) {
        super(msg);
    }

}
