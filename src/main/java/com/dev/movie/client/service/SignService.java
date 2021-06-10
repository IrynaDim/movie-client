package com.dev.movie.client.service;

import com.dev.movie.client.entity.JwtToken;
import com.dev.movie.client.exception.ClientErrorDecoder;
import com.dev.movie.client.service.feign.SignClient;
import com.dev.movie.client.logger.Slf4jLogger;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SignService {
    private final SignClient signClient;

    public SignService(@Value("${sign.in.url}") String url) {
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
