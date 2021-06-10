package com.dev.movie.client.service;

import com.dev.movie.client.entity.JwtToken;
import com.dev.movie.client.exception.ClientErrorDecoder;
import com.dev.movie.client.service.feign.SignClient;
import com.dev.movie.client.logger.Slf4jLogger;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignService {
    private static final SignClient signClient;
    private static final String url = "http://localhost:8080/signin"; // не писать путь.через value Mapi глянуть. BeanCofig -> Value

    static {
        signClient = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .errorDecoder(new ClientErrorDecoder())
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.BASIC)
                .target(SignClient.class, url);
    }

    public JwtToken in(String email, String password) {
        return signClient.in(email, password);
    }
}
