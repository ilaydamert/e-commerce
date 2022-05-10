package com.ecommerce.ecommerce.DTOs.Cart;

import com.sun.istack.NotNull;

public class CartDeleteDto {
    private @NotNull Integer id;
    private @NotNull String token;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id= id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public CartDeleteDto() {
    }
}