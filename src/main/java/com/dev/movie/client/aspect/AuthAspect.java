package com.dev.movie.client.aspect;

import com.dev.movie.client.entity.TokenStorage;
import com.dev.movie.client.exception.UnauthorizedException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AuthAspect {

    @Pointcut("execution(* com.dev.movie.client.service.ClientService.findAllHall(..)))")
    public void movieMethod() {
    }

    @Pointcut("execution(* com.dev.movie.client.service.ClientService.findAllMovies(..)))")
    public void cinemaHallMethod() {
    }

    @Before("(cinemaHallMethod() || movieMethod()) && !@annotation(com.dev.movie.client.config.AllUser)")
    public void checkUserToken(JoinPoint joinPoint) {
        Object[] values = joinPoint.getArgs();
        for (Object value : values) {
            if (TokenStorage.class.equals(value.getClass()) && ((TokenStorage) value).getToken() == null) {
                throw new UnauthorizedException();
            }
        }
    }
}
