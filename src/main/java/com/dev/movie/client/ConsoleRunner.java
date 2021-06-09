package com.dev.movie.client;

import com.dev.movie.client.command.*;
import com.dev.movie.client.entity.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.util.Scanner;

@Component
public class ConsoleRunner implements ApplicationRunner {
    SignInCommand signInCommand;
    RegistrationCommand registrationCommand;
    FindAllMoviesCommand findAllMoviesCommand;
    FindAllCinemaHallCommand findAllCinemaHallsCommand;

    public ConsoleRunner(SignInCommand signInCommand, RegistrationCommand registrationCommand,
                         FindAllMoviesCommand findAllMoviesCommand, FindAllCinemaHallCommand findAllCinemaHallsCommand) {
        this.signInCommand = signInCommand;
        this.registrationCommand = registrationCommand;
        this.findAllMoviesCommand = findAllMoviesCommand;
        this.findAllCinemaHallsCommand = findAllCinemaHallsCommand;
    }

    @Override
    public void run(ApplicationArguments args) {
        JwtToken jwtToken = JwtToken.getInstance("");
        PrintStream printStream = System.out;

        Invoker invoker = new Invoker();
        invoker.register("1", signInCommand);
        invoker.register("2", registrationCommand);
        invoker.register("3", findAllMoviesCommand);
        invoker.register("4", findAllCinemaHallsCommand);
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            printStream.println("\n Please, choose from the next commands:"
                    + "\n \"1\" - to sing-in into the program"
                    + "\n \"2\" - to register new user"
                    + "\n \"q\" - to quit from the app. \n");
            command = scanner.nextLine();
            if (command.equals("q")) {
                printStream.println("Buy-buy!");
                break;
            }
            invoker.execute(command, scanner, printStream, jwtToken); // + loopHandler
            while (true) {
                printStream.println("\n Please, choose from the next commands:"
                        + "\n \"3\" - to show all movies"
                        + "\n \"4\" - to show all cinema-halls"
                        + "\n \"q\" - to quit from the app. \n");
                command = scanner.nextLine();
                if (command.equals("q")) {
                    printStream.println("Buy-buy!");
                    break;
                }
                invoker.execute(command, scanner, printStream, jwtToken);
            }
        }
    }
}
