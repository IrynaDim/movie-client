package com.dev.movie.client.service.feign;

import com.dev.movie.client.entity.*;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface MovieClient {
    @RequestLine("GET /cinema-halls")
    List<CinemaHall> findAllHalls();

    @RequestLine("GET /movies")
    List<Movie> findAllMovies();

    @RequestLine("POST /movies")
    @Headers("Content-Type: application/json")
    Movie createMovie(Movie movie);

    @RequestLine("POST /registration")
    @Headers("Content-Type: application/json")
    void registration(UserRegistration user);

    @RequestLine("POST /signin?" +
            "email={email}&" +
            "password={password}")
    @Headers("Content-Type: application/json")
    Jwt in(@Param("email") String email,
           @Param("password") String password);
}
