package com.dev.movie.client.command;

import com.dev.movie.client.entity.Movie;

import java.util.Scanner;

//ConcreteCommand
public class AddCommand implements Command {
    MovieCommand movieCommand;

    public AddCommand(MovieCommand movieCommand) {
        this.movieCommand = movieCommand;
    }

    @Override
    public void execute() {
        movieCommand.create();
    }
}
