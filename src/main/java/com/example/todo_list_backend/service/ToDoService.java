package com.example.todo_list_backend.service;

import com.example.todo_list_backend.model.ToDo;

public interface ToDoService {
    ToDo create(ToDo toDo);

    ToDo edit(ToDo oldToDo);

    void delete(int toDoId);

    ToDo changeDoneToTrue(ToDo toDo);

    ToDo changeDoneToFalse(ToDo toDo);
}
