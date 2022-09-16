package com.example.weedingplaylist.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(value = {TrackAlreadyExistException.class})
    public ResponseEntity<Object> handleApiRequestException(TrackAlreadyExistException e) {
        log.error(e.getMessage(), e);

        ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, apiException.httpStatus());
    }

    @ExceptionHandler(value = {TrackNotFoundException.class})
    public ResponseEntity<Object> handleApiRequestException(TrackNotFoundException e) {
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
}
