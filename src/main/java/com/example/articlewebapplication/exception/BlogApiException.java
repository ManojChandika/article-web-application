package com.example.articlewebapplication.exception;

import org.springframework.http.HttpStatus;

public class BlogApiException extends RuntimeException{
    public HttpStatus status;
    public String massage;

    public BlogApiException(HttpStatus status, String massage) {
        this.status = status;
        this.massage = massage;
    }

    public BlogApiException(String message, HttpStatus status, String massage) {
        super(message);
        this.status = status;
        this.massage = massage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMassage() {
        return massage;
    }
}
