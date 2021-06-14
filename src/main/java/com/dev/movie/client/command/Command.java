package com.dev.movie.client.command;

import java.io.PrintStream;
import java.util.Scanner;

public interface Command {
    void execute(Scanner scanner, PrintStream printStream, LoopHandler loopHandler);
    String getName();
    String getDescription();
}
