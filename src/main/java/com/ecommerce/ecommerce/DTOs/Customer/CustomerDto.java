package com.ecommerce.ecommerce.DTOs.Customer;

import com.ecommerce.ecommerce.Models.Customer;
import com.sun.istack.NotNull;

public class CustomerDto {


    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String username;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
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
    public CustomerDto(Customer customer) {
        this.setId(customer.getId());
        this.setFirstName(customer.getFirstName());
        this.setLastName(customer.getLastName());
        this.setPassword(customer.getPassword());
        this.setUsername(customer.getUsername());
        this.setEmail(customer.getEmail());
    }
    public CustomerDto(CustomerDto customerDto) {
        this.setFirstName(customerDto.getFirstName());
        this.setLastName(customerDto.getLastName());
        this.setPassword(customerDto.getPassword());
        this.setUsername(customerDto.getUsername());
        this.setEmail(customerDto.getEmail());
    }
    public CustomerDto(@NotNull String firstName, @NotNull String lastName, @NotNull String username,@NotNull String password,@NotNull String email) {
        this.firstName = firstName;
        this.lastName= lastName;
        this.username = username;
        this.password=password;
        this.email=email;
    }

    public CustomerDto(){}

}
