package com.dev.movie.client.command;

import com.dev.movie.client.entity.UserRegistration;
import com.dev.movie.client.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.Scanner;

@Service
@AllArgsConstructor
public class RegistrationCommand implements Command {
    private final ClientService service;

    @Override
    public void execute(Scanner scanner, PrintStream printStream, LoopHandler handler) {
        UserRegistration user = new UserRegistration();
        printStream.println("Enter email: ");
        String email = scanner.nextLine();
        user.setEmail(email);
        printStream.println("Enter password (\n" +
                "at least 4 characters): ");
        String password = scanner.nextLine();
        user.setPassword(password);
        printStream.println("Repeat password: ");
        String repeatPassword = scanner.nextLine();
        user.setRepeatPassword(repeatPassword);
        service.registration(user);
    }

    @Override
    public String getName() {
        return "3";
    }

    @Override
    public String getDescription() {
        return "to register new user";
    }
}
