package com.example.todo_list_backend.service;

import com.example.todo_list_backend.model.Priority;
import com.example.todo_list_backend.model.ToDo;
import com.example.todo_list_backend.repository.ToDoRepository;
import com.example.todo_list_backend.repository.ToDoRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
    Once database is ready, implement the service methods correctly.
 */

@Service
public class ToDoServiceImpl implements ToDoService{

    @Autowired
    ToDoRepository toDoRepository;

    @Override
    public ToDo create(ToDo toDo) {
        return toDoRepository.save(toDo);
    }

    @Override
    public ToDo edit(ToDo newToDo) {
        return toDoRepository.save(newToDo);
    }

    @Override
    public void delete(int toDoId) {
        toDoRepository.deleteById(toDoId);
    }

    public ToDo changeDoneToTrue(ToDo toDo) {
        if (!toDo.isDone()) {
            toDo.setDone(true);
            toDo.setDateDone(java.time.LocalDate.now());
        }
        return edit(toDo);
    }

    public ToDo changeDoneToFalse(ToDo toDo) {
        if (toDo.isDone()) {
            toDo.setDone(false);
            toDo.setDateDone(null);
        }
        return edit(toDo);
    }

}
