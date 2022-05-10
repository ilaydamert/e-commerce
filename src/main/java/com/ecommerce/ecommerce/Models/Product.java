package com.ecommerce.ecommerce.Models;
import com.ecommerce.ecommerce.DTOs.Product.ProductCreateDto;
import com.ecommerce.ecommerce.DTOs.Product.ProductDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="stock")
    private int stock;
    @Column(name="name")
    private String name;
    @Column(name="price")
    private double price;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<Cart> carts;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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
    public Product(){}
    public Product(int id,int stock, String name, double price){
        this.id=id;
        this.stock=stock;
        this.name=name;
        this.price=price;
    }
    public Product(ProductDto productDto) {
        this.id=productDto.getId();
        this.name = productDto.getName();
        this.price = productDto.getPrice();
        this.stock=productDto.getStock();
    }
    public Product(ProductCreateDto productDto) {
        this.name = productDto.getName();
        this.price = productDto.getPrice();
        this.stock=productDto.getStock();
    }





}
