package com.dev.movie.client.service;

import com.dev.movie.client.entity.CinemaHall;
import com.dev.movie.client.feign.CinemaHallClient;
import com.dev.movie.client.logger.Log4J;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

import java.util.List;

public class CinemaHallService {
    private static final CinemaHallClient cinemaHallClient;
    private static final String url = "http://localhost:8080/cinema-halls"; // не писать путь.через value Mapi глянуть. BeanCofig -> Value

    static {
        cinemaHallClient = Feign.builder()
                .encoder(new GsonEncoder()) //.requestInterceptor() - добавить. передаем наш настроенный интерсептор
                .decoder(new GsonDecoder())
                .logger(new Log4J())
                .logLevel(Logger.Level.BASIC)
                .target(CinemaHallClient.class, url);
    }
      public List<CinemaHall> findAll(String token){
        return cinemaHallClient.findAll(token);
      }
}
