package com.example.weedingplaylist.exception;

public class TrackNotFoundException extends RuntimeException {
    public TrackNotFoundException(String message) {
        super(message);
    }
}
