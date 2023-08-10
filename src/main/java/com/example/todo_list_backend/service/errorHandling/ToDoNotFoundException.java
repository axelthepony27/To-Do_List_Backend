package com.example.todo_list_backend.service.errorHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ToDoNotFoundException extends RuntimeException {

    public ToDoNotFoundException(String message){
        super(message);
    }
}
