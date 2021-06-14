package com.dev.movie.client.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public final class TokenStorage {
    private String token;
}
