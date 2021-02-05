package com.dev.movie.client.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.dev.movie.client.service"
})
public class AppConfig {
}
