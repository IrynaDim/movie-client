package com.dev.movie.client.exception;

import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(chain = true)
public class HttpErrorException extends RuntimeException {

}

