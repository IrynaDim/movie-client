package com.dev.movie.client.command;

import com.dev.movie.client.entity.JwtToken;
import com.dev.movie.client.entity.UserRegistration;
import com.dev.movie.client.service.RegistrationService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.Scanner;

@Service
@Data
@AllArgsConstructor
public class RegistrationCommand implements Command {
    RegistrationService registrationService;

    @Override
    public void execute(Scanner scanner, PrintStream printStream, JwtToken token) {
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
        registrationService.registration(user);
    }
}
