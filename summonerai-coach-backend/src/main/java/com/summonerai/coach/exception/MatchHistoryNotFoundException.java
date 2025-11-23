package com.summonerai.coach.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MatchHistoryNotFoundException extends RuntimeException {
    public MatchHistoryNotFoundException(String message) {
        super(message);
    }
}
