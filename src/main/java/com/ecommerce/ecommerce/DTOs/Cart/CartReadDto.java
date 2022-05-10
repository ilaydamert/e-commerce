package com.ecommerce.ecommerce.DTOs.Cart;

import com.sun.istack.NotNull;

public class CartReadDto {
    private @NotNull String token;

    public CartReadDto() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
