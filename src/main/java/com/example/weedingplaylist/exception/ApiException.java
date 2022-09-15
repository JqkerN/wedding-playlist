package com.example.weedingplaylist.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public record ApiException(String message, HttpStatus httpStatus, ZonedDateTime zonedDateTime) {

    @Override
    public String toString() {
        return "ApiException{" +
                "message='" + message + '\'' +
                ", httpStatus=" + httpStatus +
                ", zonedDateTime=" + zonedDateTime +
                '}';
    }
}
