package com.ecommerce.ecommerce.Exceptions;

public class ProductNotExistException extends IllegalArgumentException{
    public ProductNotExistException(String msg) {
        super(msg);
    }
}
