package com.dev.movie.client.service.feign;

import com.dev.movie.client.entity.JwtToken;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface SignClient {

    @RequestLine("POST ?" +
            "email={email}&" +
            "password={password}")
    @Headers("Content-Type: application/json")
    JwtToken in(@Param("email") String email,
                @Param("password") String password);
}
