package com.dev.movie.client.config;

import com.dev.movie.client.command.FindAllCinemaHallCommand;
import com.dev.movie.client.command.FindAllMoviesCommand;
import com.dev.movie.client.command.RegistrationCommand;
import com.dev.movie.client.command.SignInCommand;
import com.dev.movie.client.service.CinemaHallService;
import com.dev.movie.client.service.MovieService;
import com.dev.movie.client.service.RegistrationService;
import com.dev.movie.client.service.SignService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    SignInCommand signInCommand() {
        return new SignInCommand(new SignService());
    }

    @Bean
    RegistrationCommand registrationCommand() {
        return new RegistrationCommand(new RegistrationService());
    }

    @Bean
    FindAllMoviesCommand findAllMoviesCommand() {
        return new FindAllMoviesCommand(new MovieService());
    }

    @Bean
    FindAllCinemaHallCommand findAllCinemaHallsCommand() {
        return new FindAllCinemaHallCommand(new CinemaHallService());
    }
}
