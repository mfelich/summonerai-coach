package com.summonerai.coach.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SummonerNotFoundException extends RuntimeException {
    public SummonerNotFoundException(String message) {
        super(message);
    }
}
