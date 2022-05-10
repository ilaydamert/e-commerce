package com.ecommerce.ecommerce.DTOs.Customer;

import com.ecommerce.ecommerce.Models.Customer;
import com.sun.istack.NotNull;

public class CustomerCreateDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String username;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public CustomerCreateDto(Customer customer) {
        this.setFirstName(customer.getFirstName());
        this.setLastName(customer.getLastName());
        this.setPassword(customer.getPassword());
        this.setUsername(customer.getUsername());
        this.setEmail(customer.getEmail());
    }
    public CustomerCreateDto(CustomerDto customerDto) {
        this.setFirstName(customerDto.getFirstName());
        this.setLastName(customerDto.getLastName());
        this.setPassword(customerDto.getPassword());
        this.setUsername(customerDto.getUsername());
        this.setEmail(customerDto.getEmail());
    }

    public CustomerCreateDto(@NotNull String firstName, @NotNull String lastName, @NotNull String username,@NotNull String email, @NotNull String password) {
        this.firstName = firstName;
        this.lastName= lastName;
        this.username = username;
        this.email = email;
        this.password=password;
    }

    public CustomerCreateDto(){}
}
