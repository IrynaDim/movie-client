package com.dev.movie.client.service;

import com.dev.movie.client.config.AllUser;
import com.dev.movie.client.entity.*;
import com.dev.movie.client.exception.ClientErrorDecoder;
import com.dev.movie.client.logger.Slf4jLogger;
import com.dev.movie.client.service.feign.FeignClient;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final FeignClient feignClient;

    public ClientService(@Value("${url}") String url, TokenStorage tokenStorage) {
        this.feignClient = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .errorDecoder(new ClientErrorDecoder())
                .requestInterceptor(new TokenRequestInterceptor(tokenStorage))
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.BASIC)
                .target(FeignClient.class, url);
    }

    public List<CinemaHall> findAllHall() {
        return feignClient.findAllHalls();
    }

    public List<Movie> findAllMovies() {
        return feignClient.findAllMovies();
    }

    public void addMovie(Movie movie) {
        feignClient.createMovie(movie);
    }

    @AllUser
    public void registration(UserRegistration user) {
        feignClient.registration(user);
    }

    @AllUser
    public Jwt in(String email, String password) {
        return feignClient.in(email, password);
    }
}
