package com.learn.realtime.springboot.inventorylisting.advice;

import com.learn.realtime.springboot.inventorylisting.error.ErrorResponse;
import com.learn.realtime.springboot.inventorylisting.error.InventoryAppErrorStatus;
import com.learn.realtime.springboot.inventorylisting.exception.DuplicateProductInsertionError;
import com.learn.realtime.springboot.inventorylisting.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({DuplicateProductInsertionError.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(DuplicateProductInsertionError exception){

        ErrorResponse errorResponse  = new ErrorResponse(LocalDateTime.now(), InventoryAppErrorStatus.B002, HttpStatus.CONFLICT, exception.getMessage() );

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(errorResponse);
    }
}
