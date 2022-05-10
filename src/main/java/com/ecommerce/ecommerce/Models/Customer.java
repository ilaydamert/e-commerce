package com.ecommerce.ecommerce.Models;

import com.ecommerce.ecommerce.DTOs.Customer.CustomerCreateDto;
import com.ecommerce.ecommerce.DTOs.Customer.CustomerDto;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(	name = "customers")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "password")
    private String password;
    @Column(name = "username")
    private String username;
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;



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
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Customer(String firstName, String lastName, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password=password;
    }
    public Customer(String firstName, String lastName, String email, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
    }

    public Customer() {
    }
    public Customer(CustomerDto customerDto) {
        this.firstName = customerDto.getFirstName();
        this.lastName = customerDto.getLastName();
        this.email= customerDto.getEmail();
        this.username= customerDto.getUsername();
        this.password= customerDto.getPassword();
    }
    public Customer(CustomerCreateDto customerCreateDto) {
        this.firstName = customerCreateDto.getFirstName();
        this.lastName = customerCreateDto.getLastName();
        this.email= customerCreateDto.getEmail();
        this.username= customerCreateDto.getUsername();
        this.password= customerCreateDto.getPassword();
    }

}
