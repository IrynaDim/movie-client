package com.dev.movie.client.logger;

import feign.Logger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Slf4jLogger extends Logger {

    @Override
    protected void log(String configKey, String format, Object... args) {
        log.info(String.format(methodTag(configKey) + format, args));
    }
}
