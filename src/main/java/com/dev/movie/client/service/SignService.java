package com.dev.movie.client.service;

import com.dev.movie.client.entity.JwtToken;
import com.dev.movie.client.feign.SignClient;
import com.dev.movie.client.logger.Log4J;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

public class SignService {
    private static final SignClient signClient;
    private static final String url = "http://localhost:8080/signin"; // не писать путь.через value Mapi глянуть. BeanCofig -> Value

    static {
        signClient = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Log4J())
                .logLevel(Logger.Level.BASIC)
                .target(SignClient.class, url);
    }

    public JwtToken in(String email, String password) {
        return signClient.in(email, password);
    }
}
