package com.dev.movie.client.command;

//ConcreteCommand
public class FindAllCommand implements Command {
    MovieCommand movieCommand;

    public FindAllCommand(MovieCommand movieCommand) {
        this.movieCommand = movieCommand;
    }

    @Override
    public void execute() {
        movieCommand.getAll();
    }
}
