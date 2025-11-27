package com.summonerai.coach.exception;

import org.springframework.ai.openai.api.common.OpenAiApiClientErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OpenAiApiClientErrorException.class)
    public ResponseEntity<ErrorDetails> handleOpenAiException(OpenAiApiClientErrorException exception,
                                                              WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                "The AI service is currently unavailable. Please try again later.",
                request.getDescription(false),
                "AI_SERVICE_UNAVAILABLE"
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                              WebRequest request) {

        String message = exception.getBindingResult().getFieldError().getDefaultMessage();

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                message,
                request.getDescription(false),
                "VALIDATION_EXCEPTION"
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SummonerNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleSummonerNotFoundError(SummonerNotFoundException exception,
                                                                    WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false),
                "RIOT_API_EXCEPTION"
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RiotApiServerErrorException.class)
    public ResponseEntity<ErrorDetails> handleRiotApiServerErrorException(RiotApiServerErrorException exception,
                                                                          WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false),
                "RIOT_API_SERVICE_ERROR"
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MatchHistoryNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleRiotApiServerErrorException(MatchHistoryNotFoundException exception,
                                                                          WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false),
                "MATCH_HISTORY_NOT_FOUND"
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MatchStatsNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleRiotApiServerErrorException(MatchStatsNotFoundException exception,
                                                                          WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false),
                "MATCH_STATS_NOT_FOUND"
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

}

