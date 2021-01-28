package com.dev.movie.client.feign;

import com.dev.movie.client.entity.Movie;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

import java.util.List;

@FeignClient(name = "movieFeignClient")
public interface MovieClient {
    @RequestLine("GET ?token={token}")
    List<Movie> findAll(@Param("token") String token);

    @RequestLine("POST ?token={token}")
    @Headers("Content-Type: application/json")
    Movie create(@Param("token") String token,
                 Movie movie);
}
