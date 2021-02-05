package com.dev.movie.client.feign;

import com.dev.movie.client.entity.CinemaHall;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

import java.util.List;

@FeignClient(name = "cinemaHallFeignClient")
public interface CinemaHallClient {
    @RequestLine("GET ?token={token}")
    List<CinemaHall> findAll(@Param("token") String token);
}
