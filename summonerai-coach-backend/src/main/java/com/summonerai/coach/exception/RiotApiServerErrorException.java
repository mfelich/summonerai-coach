package com.summonerai.coach.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class RiotApiServerErrorException extends RuntimeException {
    public RiotApiServerErrorException(String message) {
        super(message);
    }
}
