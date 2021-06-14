package com.dev.movie.client;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@ComponentScan
@PropertySource("classpath:loader.properties")
@EnableAspectJAutoProxy
public class Main {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Main.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
