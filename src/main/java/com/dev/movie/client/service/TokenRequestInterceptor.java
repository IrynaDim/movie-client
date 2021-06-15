package com.dev.movie.client.service;

import com.dev.movie.client.entity.TokenStorage;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TokenRequestInterceptor implements RequestInterceptor {
    private final TokenStorage tokenStorage;

    @Override
    public void apply(RequestTemplate template) {
        template.query("token", tokenStorage.getToken());
    }
}

