package com.dev.movie.client.entity;

import lombok.Data;

@Data
public class UserRegistration {
    private String email;
    private String password;
    private String repeatPassword;
}
