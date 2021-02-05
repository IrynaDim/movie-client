package com.dev.movie.client;

import com.dev.movie.client.command.*;
import com.dev.movie.client.config.AppConfig;
import com.dev.movie.client.entity.JwtToken;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.PrintStream;
import java.util.Scanner;

// TODO: фреймоврк для написания консольных команд
// контейнер Spring IoC, он перед циклом вызывается.

public class Main {
    public static void main(String[] args) {
        JwtToken jwtToken = JwtToken.getInstance("");
        PrintStream printStream = System.out;

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        SignInCommand signInCommand = context.getBean(SignInCommand.class);
        RegistrationCommand registrationCommand = context.getBean(RegistrationCommand.class);
        FindAllMoviesCommand findAllMoviesCommand = context.getBean(FindAllMoviesCommand.class);
        FindAllCinemaHallCommand findAllCinemaHallsCommand = context.getBean(FindAllCinemaHallCommand.class);

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
            invoker.execute(command, scanner, printStream, jwtToken);
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
