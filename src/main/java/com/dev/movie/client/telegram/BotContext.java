package com.dev.movie.client.telegram;

public class BotContext { // он собирает в себе три сущности, комплексный объект
    private final ChatBot bot; // реализует бота
    private final UserBotStorage user;
    private final String input;
    private final BotService service;

    public static BotContext of(ChatBot bot, UserBotStorage user, String text, BotService service) {
        return new BotContext(bot, user, text, service);
    }

    private BotContext(ChatBot bot, UserBotStorage user, String input, BotService service) {
        this.bot = bot;
        this.user = user;
        this.input = input;
        this.service = service;
    }

    public ChatBot getBot() {
        return bot;
    }

    public UserBotStorage getUser() {
        return user;
    }

    public String getInput() {
        return input;
    }

    public void getToken () {
        service.run("4");
    }

    public String getMovies () {
        return service.run("1");
    }

    public String getHalls () {
        return service.run("2");
    }
}
