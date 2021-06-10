package com.dev.movie.client.service;

import com.dev.movie.client.entity.Movie;
import com.dev.movie.client.exception.ClientErrorDecoder;
import com.dev.movie.client.service.feign.MovieClient;
import com.dev.movie.client.logger.Slf4jLogger;
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

    public MovieService(@Value("${movie.url}") String url) {
        movieClient = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .errorDecoder(new ClientErrorDecoder())
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.BASIC)
                .target(MovieClient.class, url);
    }

    public List<Movie> findAll(String token) {
        return movieClient.findAll(token);
    }

    public void add(Movie movie, String token) {
        movieClient.create(token, movie);
    }
}
