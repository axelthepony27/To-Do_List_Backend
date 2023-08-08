package com.example.todo_list_backend.controller;

import com.example.todo_list_backend.model.ToDo;
import com.example.todo_list_backend.repository.ToDoRepositoryImpl;
import com.example.todo_list_backend.service.ToDoServiceImpl;
import com.example.todo_list_backend.service.errorHandling.ToDoNotFoundException;
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
        return new ResponseEntity<>(JsonHandler.toJson(toDoRepository.toDoMap), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<String> getToDo(@PathVariable int id) {
        if(toDoRepository.existsById(id)) {
            return new ResponseEntity<>(JsonHandler.toJson(toDoRepository.findById(id)), HttpStatus.OK);
        }else {
            throw new ToDoNotFoundException(String.format("Couldn't find ToDo with ID: %d", id));
        }
    }

    @PostMapping("")
    private ResponseEntity<String> crateToDo(@Valid @RequestBody ToDo toDo) {
        toDoRepository.save(toDoService.create(toDo));
        return new ResponseEntity<>(JsonHandler.toJson(toDoRepository.toDoMap), HttpStatus.OK);
    }


    @PutMapping("/{id}/done")
    private ResponseEntity<String> doToDo(@PathVariable int id){
        if(toDoRepository.existsById(id)) {
            ToDo editedToDo = toDoService.changeDoneToTrue(toDoRepository.findById(id));
            return new ResponseEntity<>(JsonHandler.toJson(editedToDo), HttpStatus.OK);
        }else {
            throw new ToDoNotFoundException(String.format("Couldn't find ToDo with ID: %d", id));
        }
    }

    @PutMapping("/{id}/undone")
    private ResponseEntity<String> undoToDo(@PathVariable int id){
        if(toDoRepository.existsById(id)) {
            ToDo editedToDo = toDoService.changeDoneToFalse(toDoRepository.findById(id));
            return new ResponseEntity<>(JsonHandler.toJson(editedToDo), HttpStatus.OK);
        }else {
            throw new ToDoNotFoundException(String.format("Couldn't find ToDo with ID: %d", id));
        }
    }

}
