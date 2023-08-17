package com.example.todo_list_backend.service.errorHandling;

import java.time.LocalDateTime;

public class CustomErrorMessage {

    private final LocalDateTime timestamp;
    private final int statusCode;
    private final String error;
    private final String message;

    private final String description;


    public CustomErrorMessage(LocalDateTime timestamp,
                              int statusCode,
                              String error,
                              String message,
                              String description) {
        this.timestamp = timestamp;
        this.statusCode = statusCode;
        this.error = error;
        this.message = message;
        this.description = description;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }

    public String getError() {
        return error;
    }
}
