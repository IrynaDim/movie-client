package com.dev.movie.client.command;

import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.Scanner;

@Service
public class ExitCommand implements Command{

    @Override
    public void execute(Scanner scanner, PrintStream printStream, LoopHandler loopHandler) {
        loopHandler.setExit(true);
    }

    @Override
    public String getName() {
        return "q";
    }

    @Override
    public String getDescription() {
        return "to exit from the app";
    }
}
