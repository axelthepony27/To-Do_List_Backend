package com.example.todo_list_backend.service;

import com.example.todo_list_backend.model.Priority;
import com.example.todo_list_backend.model.ToDo;

import java.time.LocalDate;

public interface ToDoService {
    ToDo create(String text, Priority priority, LocalDate dueDate);
    ToDo edit(ToDo oldToDo);
    void delete(int toDoId);

}
