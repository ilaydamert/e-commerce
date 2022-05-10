package com.ecommerce.ecommerce.DTOs.Cart;

import com.ecommerce.ecommerce.Models.Cart;
import com.ecommerce.ecommerce.Models.Product;
import com.sun.istack.NotNull;

public class read {
    private int id;
    private @NotNull int quantity;
    private @NotNull Integer productId;
    private @NotNull String productName;
    private @NotNull Integer productStock;
    private @NotNull double productPrice;

    public read(int id, int quantity, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.productId = product.getId();
        this.productName = product.getName();
        this.productStock = product.getStock();
        this.productPrice = product.getPrice();
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}
