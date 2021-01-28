package com.dev.movie.client.command;

import com.dev.movie.client.entity.Movie;
import com.dev.movie.client.service.MovieService;
import java.io.PrintStream;
import java.util.Scanner;

public class AddCommand implements Command {
    MovieService movieService;

    public AddCommand(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public void execute(Scanner scanner, PrintStream printStream, String token) {
        Movie movie = new Movie();
        printStream.println("Enter the title of the movie");
        movie.setTitle(scanner.nextLine());
        printStream.println("Enter the description of the movie");
        movie.setDescription(scanner.nextLine());
        movieService.add(movie, token);
    }
}
