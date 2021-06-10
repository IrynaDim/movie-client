package com.dev.movie.client.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class ClientErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        int status = response.status();

        if (status == 401) {
            return new UnauthorizedException();
        }

        if (status == 403) {
            return new NotCorrectDataException();
        }
        else {
            return new HttpErrorException();
        }
    }
}
