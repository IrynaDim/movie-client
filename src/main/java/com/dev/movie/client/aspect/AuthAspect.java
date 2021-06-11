package com.dev.movie.client.aspect;

import com.dev.movie.client.exception.UnauthorizedException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AuthAspect {

    @Pointcut("within(com.dev.movie.client.service.CinemaHallService))")
    public void cinemaHallMethod() {
    }

    @Pointcut("within(com.dev.movie.client.service.MovieService)")
    public void movieMethod() {
    }

    @Before("cinemaHallMethod() || movieMethod()")
    public void checkUserToken(JoinPoint joinPoint) {
        Object[] values = joinPoint.getArgs();
        if (values[0].equals("")) {
            throw new UnauthorizedException();
        }
    }
}
