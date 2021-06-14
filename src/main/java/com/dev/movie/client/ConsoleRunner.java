package com.dev.movie.client;

import com.dev.movie.client.command.*;
import com.dev.movie.client.exception.NotCorrectDataException;
import com.dev.movie.client.exception.UnauthorizedException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleRunner implements ApplicationRunner {
    private final List<Command> commands;

    public ConsoleRunner(List<Command> commands) {
        this.commands = commands;
    }

    @Override
    public void run(ApplicationArguments args) {
        PrintStream printStream = System.out;

        Invoker invoker = new Invoker();
        for (Command command : commands) {
            invoker.register(command.getName(), command);
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            LoopHandler loopHandler = new LoopHandler();
            printStream.println("\n Please, choose from the next commands:");
            for (Command command : commands) {
                printStream.println(command.getName() + " - " + command.getDescription());
            }
            String command = scanner.nextLine();

            try {
                invoker.execute(command, scanner, printStream, loopHandler);
            } catch (UnauthorizedException e) {
                printStream.println("You should sign-in in the system or register a new user. Press 3 or 4.");
            } catch (NotCorrectDataException e) {
                printStream.println("Incorrect login or email. Try again.");
            }
            if (loopHandler.isExit()) {
                break;
            }
        }
    }
}
