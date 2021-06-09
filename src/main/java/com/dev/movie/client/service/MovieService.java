package com.dev.movie.client.service;

import com.dev.movie.client.entity.Movie;
import com.dev.movie.client.feign.MovieClient;
import com.dev.movie.client.logger.Slf4jLogger;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private static final MovieClient movieClient;
    private static final String url = "http://localhost:8080/movies"; // не писать путь.через value Mapi глянуть. BeanCofig -> Value

    static {
        movieClient = Feign.builder()
                .encoder(new GsonEncoder()) //.requestInterceptor() - добавить. передаем наш настроенный интерсептор
                .decoder(new GsonDecoder())
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
