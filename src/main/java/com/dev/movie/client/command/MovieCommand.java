package com.dev.movie.client.command;

import java.util.Scanner;
import com.dev.movie.client.entity.Movie;
import com.dev.movie.client.fiegn.MovieClient;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

//Receiver
@EnableFeignClients
public class MovieCommand {
    private static final MovieClient movieClient;
    private static final String url = "http://localhost:8080/movies";

    static {
        movieClient = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Logger.JavaLogger().appendToFile("logs.log"))
                .logLevel(Logger.Level.BASIC)
                .target(MovieClient.class, url);
    }

    public void getAll() {
        movieClient.findAll()
                .forEach(movie -> System.out.println(movie.toString()));
    }

    public void create() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the title of the movie");
        String title = scanner.nextLine();
        System.out.println("Enter the description of the movie");
        String description = scanner.nextLine();
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setDescription(description);
        movieClient.create(movie);
    }
}
