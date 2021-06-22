package com.dev.movie.client.telegram;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public final class UserBotStorage {
    private String email;
    private String password;
    private Long chatId;
    private int stateId; // в каком состонии находится текущий юзер, чтоб знать какой State ему предлагать
}
