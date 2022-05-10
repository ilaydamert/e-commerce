package com.ecommerce.ecommerce.DTOs.Product;

import com.ecommerce.ecommerce.Models.Product;
import com.sun.istack.NotNull;

public class ProductCreateDto {

    private @NotNull
    String name;
    private @NotNull
    double price;
    private @NotNull
    Integer stock;

    public ProductCreateDto(Product product) {
        this.setName(product.getName());
        this.setPrice(product.getPrice());
        this.setStock(product.getStock());
    }
    public ProductCreateDto(@NotNull String name, @NotNull double price, @NotNull Integer stock) {

        this.name = name;
        this.price = price;
        this.stock = stock;
    }
    public ProductCreateDto(ProductCreateDto productCreateDto) {

        this.name = productCreateDto.getName();
        this.price = productCreateDto.getPrice();
        this.stock = productCreateDto.getStock();
    }

    public ProductCreateDto() {
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
