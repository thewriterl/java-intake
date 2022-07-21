package com.example.javaintake.utils;

import com.example.javaintake.utils.exception.TedTalkErrorMessage;
import com.example.javaintake.utils.exception.TedTalkException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TedTalkExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger exceptionLogger = LoggerFactory.getLogger(TedTalkExceptionHandler.class);

    @ExceptionHandler(TedTalkException.class)
    protected ResponseEntity<Object> handleCheckedException(TedTalkException ex) {
        exceptionLogger.error("Caught checked exception: {}", ex.getMessage());
        if (ex.getHttpStatus().equals(HttpStatus.NOT_FOUND)) {
            return ResponseEntity.status(ex.getHttpStatus()).build();
        }
        return ResponseEntity.status(ex.getHttpStatus()).body(new TedTalkErrorMessage(ex.getMessage()));
    }
}
