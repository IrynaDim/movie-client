package com.dev.movie.client.command;

import com.dev.movie.client.entity.JwtToken;

import java.io.PrintStream;
import java.util.Scanner;

public interface Command {
    void execute(Scanner scanner, PrintStream printStream, JwtToken token, LoopHandler loopHandler);
    String getName();
    String getDescription();
}
