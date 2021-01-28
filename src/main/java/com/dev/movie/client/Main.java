package com.dev.movie.client;

import com.dev.movie.client.command.AddCommand;
import com.dev.movie.client.command.FindAllCommand;
import com.dev.movie.client.command.Invoker;
import com.dev.movie.client.service.MovieService;
import java.io.PrintStream;
import java.util.Scanner;

// фреймоврк для написания консольных команд

public class Main {
    public static void main(String[] args) {
        PrintStream printStream = new PrintStream(System.out);
        MovieService movieService = new MovieService();
        Invoker invoker = new Invoker();
        invoker.register("1", new FindAllCommand(movieService));
        invoker.register("2", new AddCommand(movieService));

        while (true) {
            printStream.println("\n Please, choose from the next commands:"
                    + "\n \"1\" - to show all movies"
                    + "\n \"2\" - to create new movie."
                    + "\n \"q\" - to quit from the app. \n");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            if (command.equals("q")) {
                printStream.println("Buy-buy!");
                break;
            }
            invoker.execute(command, scanner, printStream);
        }
    }
}
