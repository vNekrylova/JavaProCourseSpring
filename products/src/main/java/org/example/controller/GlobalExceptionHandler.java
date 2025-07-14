package org.example.controller;

import jakarta.persistence.EntityNotFoundException;
import org.example.dto.ErrorDto;
import org.example.exception.InsufficientFundsException;
import org.example.exception.InvalidOperationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleException(Exception e) {
        log.error(e.getMessage(), e);
        return new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Что-то пошло не так");
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleException(EntityNotFoundException e) {
        log.error(e.getMessage(), e);
        return new ErrorDto(HttpStatus.NOT_FOUND.value(), "Объект не найден");
    }

    @ExceptionHandler({InsufficientFundsException.class, InvalidOperationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return new ErrorDto(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
}
