package com.example.todo_list_backend.service.errorHandling;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class CustomErrorMessage {

    private LocalDateTime timestamp;
    private int statusCode;
    private String error;
    private String message;

    private String description;


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

    public String getError(){
        return error;
    }
}
