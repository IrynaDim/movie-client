package com.dev.movie.client.aspect;

import com.dev.movie.client.entity.TokenStorage;
import com.dev.movie.client.exception.UnauthorizedException;

import lombok.AllArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@AllArgsConstructor
public class AuthAspect {
    private final TokenStorage tokenStorage;

    @Pointcut("execution (* com.dev.movie.client.command.Command.execute(..))")
    public void commandMethods() {}

    @Pointcut("@annotation(com.dev.movie.client.config.LoggedUser)")
    public void loggedUser() {}

    @Before("commandMethods() && loggedUser()")
    public void checkUserToken() {
        if (tokenStorage.getToken() == null) {
            throw new UnauthorizedException();
        }
    }
}
