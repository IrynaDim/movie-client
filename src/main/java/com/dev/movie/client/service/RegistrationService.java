package com.dev.movie.client.service;

import com.dev.movie.client.entity.UserRegistration;
import com.dev.movie.client.feign.RegistrationClient;
import com.dev.movie.client.logger.Log4J;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

public class RegistrationService {
    private static final RegistrationClient registrationClient;
    private static final String url = "http://localhost:8080"; // не писать путь.через value Mapi глянуть. BeanCofig -> Value

    static {
        registrationClient = Feign.builder()
                .encoder(new GsonEncoder()) //.requestInterceptor() - добавить. передаем наш настроенный интерсептор
                .decoder(new GsonDecoder())
                .logger(new Log4J())
                .logLevel(Logger.Level.BASIC)
                .target(RegistrationClient.class, url);
    }

    public void registration (UserRegistration user) {
        registrationClient.registration(user);
    }
}