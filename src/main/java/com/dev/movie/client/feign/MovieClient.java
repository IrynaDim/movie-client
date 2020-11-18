package com.dev.movie.client.feign;

import com.dev.movie.client.entity.Movie;
import feign.Headers;
import feign.RequestLine;
import java.util.List;

public interface MovieClient {
    @RequestLine("GET /")
    List<Movie> findAll();

    @RequestLine("POST /")
    @Headers("Content-Type: application/json")
    Movie create(Movie movie);
}
