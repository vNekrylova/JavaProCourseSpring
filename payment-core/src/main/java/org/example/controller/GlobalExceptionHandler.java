package org.example.controller;

import org.example.dto.ErrorDto;
import org.example.exception.IntegrationException;
import org.example.exception.ResponseIntegrationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(IntegrationException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ErrorDto handleException(IntegrationException e) {
        log.error(e.getMessage(), e);
        return new ErrorDto(HttpStatus.BAD_GATEWAY.value(), e.getMessage());
    }

    @ExceptionHandler(ResponseIntegrationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleException(ResponseIntegrationException e) {
        log.error(e.getMessage(), e);
        return new ErrorDto(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Что-то пошло не так");
    }
}
