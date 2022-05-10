package com.ecommerce.ecommerce.DTOs.Cart;

import com.sun.istack.NotNull;

public class CartAdd {
    private @NotNull String token;
    private @NotNull Integer productId;
    private @NotNull Integer quantity;

    public CartAdd() {
    }





    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


}
