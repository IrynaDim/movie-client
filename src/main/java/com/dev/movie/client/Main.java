package com.dev.movie.client;

import java.util.Scanner;
import com.dev.movie.client.command.AddCommand;
import com.dev.movie.client.command.FindAllCommand;
import com.dev.movie.client.command.Invoker;
import com.dev.movie.client.command.MovieCommand;

public class Main {
    public static void main(String[] args) {
        MovieCommand movieCommand = new MovieCommand();
        Invoker invoker = new Invoker(new AddCommand(movieCommand), new FindAllCommand(movieCommand));
        boolean a = true;

        while (a) {
            System.out.println("\n Please, choose from the next commands:" +
                    "\n \"1\" - to show all movies" +
                    "\n \"2\" - to create new movie." +
                    "\n \"q\" - to quit from the app. \n");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            if (command.equals("1")) {
                invoker.findAll();
            } else if (command.equals("2")) {
                invoker.add();
            } else if (command.equals("q")) {
                a = false;
                System.out.println("Buy-buy!");
            }
        }
    }
}
