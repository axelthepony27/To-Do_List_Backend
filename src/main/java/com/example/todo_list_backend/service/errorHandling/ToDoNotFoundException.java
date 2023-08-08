package com.example.todo_list_backend.service.errorHandling;

public class ToDoNotFoundException extends RuntimeException {

    public ToDoNotFoundException(String message){
        super(message);
    }
}
