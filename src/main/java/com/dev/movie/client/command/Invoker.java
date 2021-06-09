package com.dev.movie.client.command;

import com.dev.movie.client.entity.JwtToken;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

@Service
public class Invoker {
    private final HashMap<String, Command> commandMap = new HashMap<>();

    public void register(String commandName, Command command) {
        commandMap.put(commandName, command);
    }

    public void execute(String commandName, Scanner scanner, PrintStream printStream, JwtToken token) {
        Command command = commandMap.get(commandName);
        if (command == null) {
            throw new IllegalStateException("No command registered for " + commandName);
        }
        command.execute(scanner, printStream, token);
    }
}
// цикл сюда перенести