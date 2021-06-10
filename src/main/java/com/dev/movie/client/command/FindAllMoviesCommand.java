package com.dev.movie.client.command;

import com.dev.movie.client.entity.JwtToken;
import com.dev.movie.client.service.MovieService;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.Scanner;

@Service
public class FindAllMoviesCommand implements Command {
    MovieService movieService;

    public FindAllMoviesCommand(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public void execute(Scanner scanner, PrintStream printStream, JwtToken token, LoopHandler handler) {
        movieService.findAll(token.token)
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
