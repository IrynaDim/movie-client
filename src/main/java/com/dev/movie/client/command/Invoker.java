package com.dev.movie.client.command;

//Invoker
public class Invoker {
    private Command addMovie;
    private Command findAllMovies;

    public Invoker(Command addMovie, Command findAll){
        this.addMovie=addMovie;
        this.findAllMovies=findAll;
    }

    public void add(){
        addMovie.execute();
    }

    public void findAll(){
        findAllMovies.execute();
    }
}
