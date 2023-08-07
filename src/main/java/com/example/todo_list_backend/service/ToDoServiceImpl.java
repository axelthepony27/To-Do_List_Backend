package com.example.todo_list_backend.service;

import com.example.todo_list_backend.model.Priority;
import com.example.todo_list_backend.model.ToDo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
    Once database is ready, implement the service methods correctly.
 */

@Service
public class ToDoServiceImpl implements ToDoService{
    @Override
    public ToDo create(String text, Priority priority, LocalDate dueDate) {
        return new ToDo(text, priority, dueDate);
    }

    @Override
    public ToDo edit(ToDo newToDo) {
        return newToDo;
    }

    @Override
    public void delete(int toDoId) {

    }

    public ToDo changeDone(ToDo toDo) {
        if (toDo.isDone()) {
            toDo.setDone(false);
            toDo.setDateDone(null);
        } else {
            toDo.setDone(true);
            toDo.setDateDone(java.time.LocalDate.now());
        }
        return edit(toDo);
    }

}
