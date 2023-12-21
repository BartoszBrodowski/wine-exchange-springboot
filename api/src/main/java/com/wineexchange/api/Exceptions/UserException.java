package com.wineexchange.api.Exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
public class UserException {
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;

    public UserException(String message, HttpStatus httpStatus, ZonedDateTime timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }
}
