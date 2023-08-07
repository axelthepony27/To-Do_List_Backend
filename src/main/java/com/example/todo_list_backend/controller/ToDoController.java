package com.example.todo_list_backend.controller;

import com.example.todo_list_backend.model.ToDo;
import com.example.todo_list_backend.repository.ToDoRepositoryImpl;
import com.example.todo_list_backend.service.ToDoServiceImpl;
import com.example.todo_list_backend.utils.JsonHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class ToDoController {

    @Autowired
    ToDoServiceImpl toDoService;

    @Autowired
    ToDoRepositoryImpl toDoRepository;

    @GetMapping("")
    private ResponseEntity<String> toDos() {
        return new ResponseEntity<>(JsonHandler.toJson(toDoRepository.toDoList), HttpStatus.OK);
    }

    @PostMapping("")
    private ResponseEntity<String> crateToDo(@Valid @RequestBody ToDo toDo) {
        ToDo newToDo = toDoService.create(toDo.getText(), toDo.getPriority(), toDo.getDueDate());
        toDoRepository.toDoList.add(newToDo);
        return new ResponseEntity<>(JsonHandler.toJson(toDoRepository.toDoList), HttpStatus.OK);
    }

}
