package com.dev.movie.client.telegram;

import com.dev.movie.client.exception.NotCorrectDataException;
import com.dev.movie.client.telegram.utils.Emoji;
import org.springframework.util.ResourceUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public enum BotState {

    Start {
        @Override
        public void enter(BotContext context) { // что делать когда попали в состояние это? шлем "привет"
            String message = "Hello! " + Emoji.valueOf("WAVE") + " Welcome to Movie Bot." + Emoji.valueOf("CLAPPER");
            sendPhoto(context, message, "pictures/hello-photo.jpg");
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
                sendMessage(context, "Not correct email or password. " + Emoji.valueOf("PENSIVE") + "Try again.");
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
                sendMessage(context, "Not valid email " + Emoji.valueOf("PENSIVE"));
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
            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
            InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
            inlineKeyboardButton1.setText("movies");
            inlineKeyboardButton1.setCallbackData("movieButton");
            inlineKeyboardButton2.setText("cinemaHalls");
            inlineKeyboardButton2.setCallbackData("cinemaHallButton");

            List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
            keyboardButtonsRow.add(inlineKeyboardButton1);
            keyboardButtonsRow.add(inlineKeyboardButton2);

            List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
            rowList.add(keyboardButtonsRow);
            inlineKeyboardMarkup.setKeyboard(rowList);

            sendMessageWithKeyBoard(context, "You can observe all movies or all cinema halls. " + Emoji.valueOf("SMILING_FACE"),
                    inlineKeyboardMarkup);
        }

        @Override
        public void handleInput(BotContext context) {
            switch (context.getInput()) {
                case "movieButton":
                    next = Movies;
                    break;
                case "cinemaHallButton":
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

    public boolean isInputNeeded() {
        return inputNeeded;
    }

    public void handleInput(BotContext context) {
        //  обрабатывает ввод пользователя в текущем состоянии
    }

    protected void sendPhoto(BotContext context, String imageCaption, String imagePath) {
        File image = null;
        try {
            image = ResourceUtils.getFile("classpath:" + imagePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        SendPhoto sendPhoto = new SendPhoto().setPhoto(image);
        sendPhoto.setChatId(context.getUser().getChatId());
        sendPhoto.setCaption(imageCaption);
        try {
            context.getBot().execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    protected void sendMessageWithKeyBoard(BotContext context, String text, InlineKeyboardMarkup inlineKeyboardMarkup) { // шлет сообщение пользователю
        SendMessage message = new SendMessage()
                .setChatId(context.getUser().getChatId())
                .setText(text)
                .setReplyMarkup(inlineKeyboardMarkup);

        try {
            context.getBot().execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
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

    public abstract void enter(BotContext context); // войти в  новое состояние. пишем что-то юзеру

    public abstract BotState nextState(); // в какое след состояние перейти
}
