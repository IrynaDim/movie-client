package com.dev.movie.client.command;

import com.dev.movie.client.entity.JwtToken;
import com.dev.movie.client.service.SignService;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.Scanner;

@Service
public class SignInCommand implements Command {
    private SignService restSignService;

    public SignInCommand(SignService restSignService) {
        this.restSignService = restSignService;
    }

    @Override
    public void execute(Scanner scanner, PrintStream printStream, JwtToken jwtToken, LoopHandler handler) {
        printStream.println("Enter your email: ");
        String email = scanner.nextLine();
        printStream.println("Enter your password: ");
        String password = scanner.nextLine();
        jwtToken.token = restSignService.in(email, password).token;
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
