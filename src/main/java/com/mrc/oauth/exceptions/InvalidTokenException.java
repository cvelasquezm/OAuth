package com.mrc.oauth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class InvalidTokenException extends Exception {

    public InvalidTokenException(String message){
        super(message);
    }
}
