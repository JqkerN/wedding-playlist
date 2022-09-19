package com.example.wedding.exception;

import javax.validation.constraints.NotBlank;

public class VoteAlreadyExistException extends RuntimeException {
    public VoteAlreadyExistException(String message) {
        super(message);
    }
}
