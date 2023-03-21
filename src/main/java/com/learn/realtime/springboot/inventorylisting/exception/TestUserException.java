package com.learn.realtime.springboot.inventorylisting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class TestUserException extends RuntimeException{

    public TestUserException(String message) {
        super(message);
    }
}
