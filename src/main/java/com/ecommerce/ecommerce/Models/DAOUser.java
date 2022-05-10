package com.ecommerce.ecommerce.Models;

import com.ecommerce.ecommerce.DTOs.Customer.CustomerDto;
import com.ecommerce.ecommerce.DTOs.User.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class DAOUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public DAOUser(String username, String password) {
        this.username = username;
        this.password=password;
    }

    public DAOUser() {
    }
    public DAOUser(UserDto userDto) {
        this.username= userDto.getUsername();
        this.password= userDto.getPassword();
    }
}
