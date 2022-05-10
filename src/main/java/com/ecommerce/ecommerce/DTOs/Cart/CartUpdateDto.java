package com.ecommerce.ecommerce.DTOs.Cart;

import com.sun.istack.NotNull;

public class CartUpdateDto {
    private @NotNull Integer id;
    private @NotNull String token;
    private @NotNull Integer productId;
    private @NotNull Integer quantity;

    public CartUpdateDto() {
    }



    @Override
    public String toString() {
        return "CartDto{" +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ",";
    }

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
