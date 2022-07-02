package com.example.javaintake.utils.exception;

import org.springframework.http.HttpStatus;

public class TedTalkException extends RuntimeException {

    public final HttpStatus httpStatus;

    public TedTalkException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
