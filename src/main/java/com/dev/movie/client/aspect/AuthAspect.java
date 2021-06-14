package com.dev.movie.client.aspect;

import com.dev.movie.client.entity.TokenStorage;
import com.dev.movie.client.exception.UnauthorizedException;

import com.dev.movie.client.service.TokenRequestInterceptor;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@AllArgsConstructor
public class AuthAspect {
    private final TokenStorage tokenStorage;

    @Pointcut("within(com.dev.movie.client.service.ClientService)")
    public void cinemaHallMethod() {
    }

    @Before("cinemaHallMethod() && !@annotation(com.dev.movie.client.config.AllUser)")
    public void checkUserToken() {
        if (tokenStorage.getToken() == null) {
            throw new UnauthorizedException();
        }
    }
}
