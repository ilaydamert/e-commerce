package com.ecommerce.ecommerce.DTOs.Product;

import com.ecommerce.ecommerce.Models.Product;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductDto {
    private Integer id;
    private @NotNull
    String name;
    private @NotNull
    double price;
    private @NotNull
    Integer stock;

    public ProductDto(Product product) {
        this.setId(product.getId());
        this.setName(product.getName());
        this.setPrice(product.getPrice());
        this.setStock(product.getStock());
    }
    public ProductDto(@NotNull int id,@NotNull String name, @NotNull double price, @NotNull Integer stock) {
        this.id=id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public ProductDto(ProductDto product) {
        this.setId(product.getId());
        this.setName(product.getName());
        this.setPrice(product.getPrice());
        this.setStock(product.getStock());
    }
    public ProductDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}