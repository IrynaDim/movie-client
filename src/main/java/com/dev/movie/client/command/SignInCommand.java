package com.dev.movie.client.command;

import com.dev.movie.client.entity.TokenStorage;
import com.dev.movie.client.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.Scanner;

@Service
@AllArgsConstructor
public class SignInCommand implements Command {
    private final MovieService service;
    private final TokenStorage token;

    @Override
    public void execute(Scanner scanner, PrintStream printStream, LoopHandler handler) {
        printStream.println("Enter your email: ");
        String email = scanner.nextLine();
        printStream.println("Enter your password: ");
        String password = scanner.nextLine();
        token.setToken(service.in(email, password).getToken());
    }

    @Override
    public String getName() {
        return "4";
    }

    @Override
    public String getDescription() {
        return "to sign in";
    }
}
