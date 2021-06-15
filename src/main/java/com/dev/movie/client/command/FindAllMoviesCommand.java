package com.dev.movie.client.command;

import com.dev.movie.client.config.LoggedUser;
import com.dev.movie.client.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.Scanner;

@Service
@AllArgsConstructor
public class FindAllMoviesCommand implements Command {
    private final MovieService service;

    @Override
    @LoggedUser
    public void execute(Scanner scanner, PrintStream printStream, LoopHandler handler) {
        service.findAllMovies()
                .forEach(movie -> printStream.println(movie.toString()));
    }

    @Override
    public String getName() {
        return "2";
    }

    @Override
    public String getDescription() {
        return "to get all movies";
    }
}
