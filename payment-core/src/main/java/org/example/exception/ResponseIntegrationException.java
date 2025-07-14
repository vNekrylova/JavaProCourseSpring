package org.example.exception;

import lombok.Getter;

@Getter
public class ResponseIntegrationException extends RuntimeException {
    private final Integer code;

    public ResponseIntegrationException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
