package com.ecommerce.ecommerce.Exceptions;

public class NotEnoughProduct extends IllegalArgumentException{
    public NotEnoughProduct(String msg) {
        super(msg);
    }
}
