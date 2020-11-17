package com.dev.movie.client.command;

import com.dev.movie.client.service.MovieService;
import java.io.PrintStream;
import java.util.Scanner;

public class FindAllCommand implements Command {
    MovieService movieService;

    public FindAllCommand(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public void execute(Scanner scanner, PrintStream printStream) {
        movieService.findAll()
                .forEach(movie -> printStream.println(movie.toString()));
    }
}
