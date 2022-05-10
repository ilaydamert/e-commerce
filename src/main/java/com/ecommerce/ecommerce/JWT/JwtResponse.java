package com.ecommerce.ecommerce.JWT;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class JwtResponse implements Serializable{
    private static final long serialVersionUID = -8091879091924046844L;
    @Autowired
    private final String jwttoken;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }
}
