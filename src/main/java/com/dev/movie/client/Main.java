package com.dev.movie.client;

import com.dev.movie.client.command.AddCommand;
import com.dev.movie.client.command.FindAllCommand;
import com.dev.movie.client.command.Invoker;
import com.dev.movie.client.entity.JwtToken;
import com.dev.movie.client.service.MovieService;
import com.dev.movie.client.service.SignService;

import java.io.PrintStream;
import java.util.Scanner;

// TODO: фреймоврк для написания консольных команд

public class Main {
    public static void main(String[] args) {
        JwtToken token = new JwtToken("");
        SignService signService = new SignService();
        MovieService movieService = new MovieService();

        PrintStream printStream = new PrintStream(System.out);
//        Invoker invoker = new Invoker();
//        invoker.register("2", new FindAllCommand(movieService));
//        invoker.register("1", new AddCommand(movieService));

        while (true) {
            printStream.println("\n Please, choose from the next commands:"
                    + "\n \"1\" - to sing-in into the program"
                    + "\n \"2\" - to show all movies"
                    + "\n \"q\" - to quit from the app. \n");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            if (command.equals("1")) {
                printStream.println("Enter your email: ");
                String email = scanner.nextLine();
                printStream.println("Enter your password: ");
                String password = scanner.nextLine();
            token = signService.in(email, password);
            }
            if (command.equals("2")) {
                movieService.findAll(token.getToken())
                        .forEach(movie -> printStream.println(movie.toString()));
            }
            if (command.equals("q")) {
                printStream.println("Buy-buy!");
                break;
            }
           // invoker.execute(command, scanner, printStream, token.getToken());
            // я закоментировала все методы из пакета command тк так мне проще было внедреять новый функционал
        }
    }
}
