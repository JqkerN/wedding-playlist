package com.example.wedding.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(value = {SongAlreadyExistException.class})
    public ResponseEntity<Object> handleApiRequestException(SongAlreadyExistException e) {
        log.error(e.getMessage(), e);

        ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, apiException.httpStatus());
    }

    @ExceptionHandler(value = {SongNotFoundException.class})
    public ResponseEntity<Object> handleApiRequestException(SongNotFoundException e) {
        log.error(e.getMessage(), e);

        ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, apiException.httpStatus());
    }

    @ExceptionHandler(value = {GuestNotFoundException.class})
    public ResponseEntity<Object> handleApiRequestException(GuestNotFoundException e) {
        log.error(e.getMessage(), e);

        ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, apiException.httpStatus());
    }

    @ExceptionHandler(value = {UserAlreadyExistException.class})
    public ResponseEntity<Object> handleApiRequestException(UserAlreadyExistException e) {
        log.error(e.getMessage(), e);

        ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, apiException.httpStatus());
    }

    @ExceptionHandler(value = {VoteAlreadyExistException.class})
    public ResponseEntity<Object> handleApiRequestException(VoteAlreadyExistException e) {
        log.error(e.getMessage(), e);

        ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, apiException.httpStatus());
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> handleApiRequestException(UserNotFoundException e) {
        log.error(e.getMessage(), e);

        ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, apiException.httpStatus());
    }
}
