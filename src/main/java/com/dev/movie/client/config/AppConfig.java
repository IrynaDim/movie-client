package com.dev.movie.client.config;

import com.dev.movie.client.aspect.AuthAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    AuthAspect messageAspect() {
        return new AuthAspect();
    }
}
