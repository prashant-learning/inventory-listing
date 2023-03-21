package com.learn.realtime.springboot.inventorylisting.exception;

public class DuplicateProductInsertionError extends RuntimeException{

    public DuplicateProductInsertionError(String message) {
        super(message);
    }
}
