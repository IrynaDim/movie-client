package com.dev.movie.client.feign;

import com.dev.movie.client.entity.UserRegistration;
import feign.Headers;
import feign.RequestLine;

public interface RegistrationClient {
    @RequestLine("POST /registration")
    @Headers("Content-Type: application/json")
    void registration(UserRegistration user);
}
