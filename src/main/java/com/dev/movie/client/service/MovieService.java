package com.dev.movie.client.service;

import com.dev.movie.client.entity.Movie;
import com.dev.movie.client.feign.MovieClient;
import com.dev.movie.client.logger.Log4J;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import java.util.List;

public class MovieService {
    private static final MovieClient movieClient;
    private static final String url = "http://localhost:8080/movies";

    static {
        movieClient = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Log4J())
                .logLevel(Logger.Level.BASIC)
                .target(MovieClient.class, url);
    }

    public List<Movie> findAll() {
        return movieClient.findAll();
    }

    public void add(Movie movie) {
        movieClient.create(movie);
    }
}
