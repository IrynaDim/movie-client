package com.dev.movie.client.command;

import java.io.PrintStream;
import java.util.Scanner;

public interface Command {
    void execute(Scanner scanner, PrintStream printStream, String token);
}
