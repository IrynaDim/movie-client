package com.dev.movie.client.logger;

import feign.Logger;
import lombok.extern.log4j.Log4j;

@Log4j
public class Log4J extends Logger {
    @Override
    protected void log(String configKey, String format, Object... args) {
        log.info(String.format(methodTag(configKey) + format, args));
    }
}
