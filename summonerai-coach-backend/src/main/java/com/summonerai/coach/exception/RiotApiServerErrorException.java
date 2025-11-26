package com.summonerai.coach.exception;

public class RiotApiServerErrorException extends RuntimeException {
    public RiotApiServerErrorException(String message) {
        super(message);
    }
}
