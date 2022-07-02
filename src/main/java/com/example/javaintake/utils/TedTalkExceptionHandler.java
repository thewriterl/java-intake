package com.example.javaintake.utils;

import com.example.javaintake.utils.exception.TedTalkErrorMessage;
import com.example.javaintake.utils.exception.TedTalkException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TedTalkExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TedTalkException.class)
    protected ResponseEntity<Object> handleCheckedException(TedTalkException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(new TedTalkErrorMessage(ex.getMessage()));
    }
}
