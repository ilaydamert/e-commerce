package com.ecommerce.ecommerce.DTOs.User;

import com.ecommerce.ecommerce.Models.DAOUser;

public class UserDto {
    private String username;
    private String password;

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
    public UserDto(){}
    public UserDto(DAOUser user){
        this.username=user.getUsername();
        this.password=user.getPassword();
    }
    public UserDto(String username, String password){
        this.username=username;
        this.password=password;
    }
}
