package com.dev.movie.client.feign;

import com.dev.movie.client.entity.JwtToken;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "signFeignClient")
public interface SignClient {

    @RequestLine("POST ?" +
            "email={email}&" +
            "password={password}")
    @Headers("Content-Type: application/json")
    JwtToken in(@Param("email") String email,
                @Param("password") String password);
}
