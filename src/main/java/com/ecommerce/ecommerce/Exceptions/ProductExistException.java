package com.ecommerce.ecommerce.Exceptions;

public class ProductExistException extends IllegalArgumentException{
    public ProductExistException(String msg) {
        super(msg);
    }
}
