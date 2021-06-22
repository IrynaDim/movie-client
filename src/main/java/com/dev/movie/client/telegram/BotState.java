package com.dev.movie.client.telegram;
import com.dev.movie.client.exception.NotCorrectDataException;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public enum BotState {

    Start {
        @Override
        public void enter(BotContext context) { // что делать когда попали в состояние это? шлем "привет"
            sendMessage(context, "Hello! Welcome to Movie Bot.");
        }
// ввода тут нет, дальше мы идем в след состояние, ввест пароль
        @Override
        public BotState nextState() {
            return EnterEmail;
        }
    },

    EnterPassword {
        private BotState next;

        @Override
        public void enter(BotContext context) {
            sendMessage(context, "Enter your password please:");
        }

        @Override
        public void handleInput(BotContext context) { // тут обрабатываем ввод
            context.getUser().setPassword(context.getInput());
            try {
                context.getToken();
                next = Approved;
            } catch (NotCorrectDataException e) {
                sendMessage(context, "Not correct email or password. Try again.");
                next = EnterEmail;
            }
        }

        @Override
        public BotState nextState() {
            return next;
        }
    },

    EnterEmail {
        private BotState next;

        @Override
        public void enter(BotContext context) {
            sendMessage(context, "Enter your e-mail please:");
        }

        @Override
        public void handleInput(BotContext context) {
            String email = context.getInput();
            try {
                InternetAddress emailAddr = new InternetAddress(email);
                emailAddr.validate();
                context.getUser().setEmail(context.getInput());
                next = EnterPassword;
            } catch (AddressException ex) {
                sendMessage(context, "Not valid email");
                next = EnterEmail;
            }
        }

        @Override
        public BotState nextState() {
            return next;
        }
    },

    Approved {
        private BotState next;

        @Override
        public void enter(BotContext context) {
            sendMessage(context, "You can observe all movies or all cinema halls. " +
                    "\n Enter: /movies or /cinemaHalls");
        }

        @Override
        public void handleInput(BotContext context) {
            switch (context.getInput()) {
                case "/movies":
                    next = Movies;
                    break;
                case "/cinemaHalls":
                    next = CinemaHalls;
                    break;
                default:
                    next = Approved;
                    break;
            }
        }

        @Override
        public BotState nextState() {
            return next;
        }
    },

    Movies(false) {
        @Override
        public void enter(BotContext context) {
            sendMessage(context, context.getMovies());
        }

        @Override
        public BotState nextState() {
            return Approved;
        }
    },

    CinemaHalls(false) {
        @Override
        public void enter(BotContext context) {
            sendMessage(context, context.getHalls());
        }

        @Override
        public BotState nextState() {
            return Approved;
        }
    };


    private static BotState[] states; // массив всем возможных значение нашего Enum
    private final boolean inputNeeded; // нужно ли ждать боту чтоб получить ответ

    BotState() {
        this.inputNeeded = true;
    }

    BotState(boolean inputNeeded) {
        this.inputNeeded = inputNeeded;
    }

    public static BotState getInitialState() { // возвращает начальное состояние. где нужно логинится
        return byId(0);
    }

    public static BotState byId(int id) { // по номеру id возвращает состоянии
        if (states == null) {
            states = BotState.values();
        }

        return states[id];
    }

    protected void sendMessage(BotContext context, String text) { // шлет сообщение пользователю
        SendMessage message = new SendMessage()
                .setChatId(context.getUser().getChatId())
                .setText(text);
        try {
            context.getBot().execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public boolean isInputNeeded() {
        return inputNeeded;
    }

    public void handleInput(BotContext context) {
        //  обрабатывает ввод пользователя в текущем состоянии
    }

    public abstract void enter(BotContext context); // войти в  новое состояние. пишем что-то юзеру
    public abstract BotState nextState(); // в какое след состояние перейти
}
