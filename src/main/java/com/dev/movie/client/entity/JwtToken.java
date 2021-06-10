package com.dev.movie.client.entity;

public final class JwtToken {
    private static JwtToken instance;
    public String token;

    private JwtToken(String token) {
        this.token = token;
    }

    public static JwtToken getInstance(String token) {
        if (instance == null) {
            instance = new JwtToken(token);
        }
        return instance;
    }
}
