package com.dev.movie.client.command;

import com.dev.movie.client.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.Scanner;

@Service
@AllArgsConstructor
public class FindAllCinemaHallCommand implements Command {
    private final ClientService service;

    @Override
    public void execute(Scanner scanner, PrintStream printStream, LoopHandler handler) {
        service.findAllHall()
                .forEach(movie -> printStream.println(movie.toString()));
    }

    @Override
    public String getName() {
        return "1";
    }

    @Override
    public String getDescription() {
        return "to get all cinema halls";
    }
}
