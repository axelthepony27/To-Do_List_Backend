package com.example.todo_list_backend.service;

import com.example.todo_list_backend.model.Priority;
import com.example.todo_list_backend.model.ToDo;

import java.time.LocalDate;

public interface ToDoService {
    ToDo create(ToDo toDo);
    ToDo edit(ToDo oldToDo);
    void delete(int toDoId);
    public ToDo changeDoneToTrue(ToDo toDo);
    public ToDo changeDoneToFalse(ToDo toDo);
}
