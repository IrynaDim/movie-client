package com.dev.movie.client.command;

import com.dev.movie.client.entity.JwtToken;
import com.dev.movie.client.service.CinemaHallService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.Scanner;

@Service
@Data
@AllArgsConstructor
public class FindAllCinemaHallCommand implements Command {
    private CinemaHallService cinemaHallService;

    @Override
    public void execute(Scanner scanner, PrintStream printStream, JwtToken token) {
        cinemaHallService.findAll(token.token)
                .forEach(movie -> printStream.println(movie.toString()));
    }
}
