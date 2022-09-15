package com.example.weedingplaylist.exception;

public class TrackAlreadyExistException extends RuntimeException {
    public TrackAlreadyExistException(String message) {
        super(message);
    }
}
