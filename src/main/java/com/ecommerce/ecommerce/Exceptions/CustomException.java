package com.ecommerce.ecommerce.Exceptions;

public class CustomException extends IllegalArgumentException{
    public CustomException(String msg) {
        super(msg);
    }
}
