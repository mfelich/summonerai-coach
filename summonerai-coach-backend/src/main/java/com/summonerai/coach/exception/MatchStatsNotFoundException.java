package com.summonerai.coach.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MatchStatsNotFoundException extends RuntimeException {
    public MatchStatsNotFoundException(String message) {
        super(message);
    }
}
