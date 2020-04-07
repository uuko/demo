package com.example.demo.models;

public class AuthencationResponse {
    public String getJwt() {
        return jwt;
    }

    public AuthencationResponse(String jwt) {
        this.jwt = jwt;
    }

    private final  String jwt;

}
