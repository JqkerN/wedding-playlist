package com.example.wedding.exception;

public class VoteAlreadyExistException extends RuntimeException {
    public VoteAlreadyExistException(String message) {
        super(message);
    }
}
