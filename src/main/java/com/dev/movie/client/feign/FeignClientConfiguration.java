package com.dev.movie.client.feign;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;

//не использую, вышло без него
public class FeignClientConfiguration {

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("user@gmail.com", "1111");
    }
}
