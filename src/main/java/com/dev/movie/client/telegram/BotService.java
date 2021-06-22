package com.dev.movie.client.telegram;
import com.dev.movie.client.command.*;
import com.dev.movie.client.exception.HttpErrorException;
import com.dev.movie.client.exception.NotCorrectDataException;
import com.dev.movie.client.exception.UnauthorizedException;
import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

@Component
public final class BotService {
    private final List<Command> commands;

    private BotService(List<Command> commands) {
        this.commands = commands;
    }

    public String run(String commandLine) {
        PrintStream printStream = System.out; // это и другое оставила, чтоб не переделывать все Команды

        Invoker invoker = new Invoker();
        for (Command command : commands) {
            invoker.register(command.getName(), command);
        }

        Scanner scanner = new Scanner(System.in);
        LoopHandler loopHandler = new LoopHandler();

        return invoker.execute(commandLine, scanner, printStream, loopHandler);
    }
}

