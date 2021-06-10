package com.dev.movie.client.service;

import com.dev.movie.client.entity.CinemaHall;
import com.dev.movie.client.exception.ClientErrorDecoder;
import com.dev.movie.client.service.feign.CinemaHallClient;
import com.dev.movie.client.logger.Slf4jLogger;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaHallService {
    private final CinemaHallClient cinemaHallClient;

    public CinemaHallService(@Value("${cinema.hall.url}") String url) {
        this.cinemaHallClient = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .errorDecoder(new ClientErrorDecoder())
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.BASIC)
                .target(CinemaHallClient.class, url);
    }

    public List<CinemaHall> findAll(String token) {
        return cinemaHallClient.findAll(token);
    }
}
