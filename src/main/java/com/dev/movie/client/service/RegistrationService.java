package com.dev.movie.client.service;

import com.dev.movie.client.entity.UserRegistration;
import com.dev.movie.client.exception.ClientErrorDecoder;
import com.dev.movie.client.service.feign.RegistrationClient;
import com.dev.movie.client.logger.Slf4jLogger;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private static final RegistrationClient registrationClient;
    private static final String url = "http://localhost:8080";

    static {
        registrationClient = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .errorDecoder(new ClientErrorDecoder())
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.BASIC)
                .target(RegistrationClient.class, url);
    }

    public void registration (UserRegistration user) {
        registrationClient.registration(user);
    }
}