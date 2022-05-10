package com.ecommerce.ecommerce.DTOs.Cart;

import com.ecommerce.ecommerce.Models.Cart;
import com.ecommerce.ecommerce.Models.Product;
import com.sun.istack.NotNull;

public class CartItemDto {
    private int id;
    private @NotNull int quantity;
    private @NotNull Integer productId;
    private @NotNull String productName;
    private @NotNull Integer productStock;
    private @NotNull double productPrice;



    public CartItemDto() {
    }

    public CartItemDto(Cart cart) {
        this.setId(cart.getId());
        this.setQuantity(cart.getQuantity());
        this.setProductId(cart.getProduct().getId());
        this.setProductName(cart.getProduct().getName());
        this.setProductStock(cart.getProduct().getStock());
        this.setProductPrice(cart.getProduct().getPrice());
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
