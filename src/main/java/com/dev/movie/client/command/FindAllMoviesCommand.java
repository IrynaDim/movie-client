package com.dev.movie.client.command;

import com.dev.movie.client.config.LoggedUser;
import com.dev.movie.client.entity.Movie;
import com.dev.movie.client.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
@AllArgsConstructor
@LoggedUser
public class FindAllMoviesCommand implements Command {
    private final MovieService service;

    @Override
    public String execute(Scanner scanner, PrintStream printStream, LoopHandler handler) {
//        service.findAllMovies()
//                .forEach(movie -> printStream.println(movie.toString()));
        StringBuilder sb = new StringBuilder("All movies list:\r\n");
        service.findAllMovies().forEach(item ->
                sb.append(item.getTitle())
                        .append(' ')
                        .append(item.getDescription())
                        .append("\r\n")
        );
        return sb.toString();
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
