package com.dev.movie.client.service;

import com.dev.movie.client.entity.*;
import com.dev.movie.client.exception.ClientErrorDecoder;
import com.dev.movie.client.logger.Slf4jLogger;
import com.dev.movie.client.service.feign.MovieClient;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieClient movieClient;

    public MovieService(@Value("${url}") String url, TokenStorage tokenStorage) {
        this.movieClient = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .errorDecoder(new ClientErrorDecoder())
                .requestInterceptor(new TokenRequestInterceptor(tokenStorage))
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.BASIC)
                .target(MovieClient.class, url);
    }

    public List<CinemaHall> findAllHall() {
        return movieClient.findAllHalls();
    }

    public List<Movie> findAllMovies() {
        return movieClient.findAllMovies();
    }

    public void addMovie(Movie movie) {
        movieClient.createMovie(movie);
    }

    public void registration(UserRegistration user) {
        movieClient.registration(user);
    }

    public Jwt in(String email, String password) {
        return movieClient.in(email, password);
    }
}
