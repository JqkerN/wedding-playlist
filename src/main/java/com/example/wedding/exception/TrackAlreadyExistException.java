package com.example.wedding.exception;

public class TrackAlreadyExistException extends RuntimeException {
    public TrackAlreadyExistException(String message) {
        super(message);
    }
}
