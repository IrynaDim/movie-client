package com.dev.movie.client.telegram;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@PropertySource("classpath:telegram.properties")
public class ChatBot extends TelegramLongPollingBot {

    private final BotService service;
    private final UserBotStorage user;
    private final String botName;
    private final String botToken;

    public ChatBot(BotService service, UserBotStorage user, @Value("${bot.name}") String botName,
                   @Value("${bot.token}") String botToken) {
        this.service = service;
        this.user = user;
        this.botName = botName;
        this.botToken = botToken;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) { // этот метод вызывается автоматичкски, когда юзер что-то вводит
        if (!update.hasMessage() && !update.getMessage().hasText()) {
            return;
        }

        final String text = update.getMessage().getText();
        final long chatId = update.getMessage().getChatId();


        BotContext context;
        BotState state;

        if (user.getChatId() == null) {
            state = BotState.getInitialState();
            user.setChatId(chatId);

            context = BotContext.of(this, user, text, service);
            state.enter(context); // войти в состоние

        } else {
            context = BotContext.of(this, user, text, service);
            state = BotState.byId(user.getStateId());

        }

        state.handleInput(context); // обработать что ввел пользователь

        do { // в цикле переходим в след состоянии
            state = state.nextState();
            state.enter(context);
        } while (!state.isInputNeeded());

        user.setStateId(state.ordinal()); // засечиваем в каком состоянии находится юзер
    }
}
