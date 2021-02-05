package com.dev.movie.client.command;

import com.dev.movie.client.entity.JwtToken;
import com.dev.movie.client.service.SignService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.Scanner;

@Service
@Data
public class SignInCommand implements Command {
    private SignService signService;

    public SignInCommand(SignService signService) {
        this.signService = signService;
    }

    @Override
    public void execute(Scanner scanner, PrintStream printStream, JwtToken jwtToken) {
        printStream.println("Enter your email: ");
        String email = scanner.nextLine();
        printStream.println("Enter your password: ");
        String password = scanner.nextLine();
        jwtToken.token = signService.in(email, password).token;
    }
}
