package com.example.todo_list_backend.service.errorHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ToDoNotFoundException.class)
    public ResponseEntity<CustomErrorMessage> handleToDoNotFound(
            ToDoNotFoundException e,
            WebRequest request) {
        CustomErrorMessage body = new CustomErrorMessage(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                e.getCause().getMessage(),
                e.getMessage(),
                request.getDescription(false)
                );
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

}
