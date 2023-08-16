package com.example.todo_list_backend.controller;

import com.example.todo_list_backend.model.ToDo;
import com.example.todo_list_backend.repository.ToDoRepository;
import com.example.todo_list_backend.service.ToDoService;
import com.example.todo_list_backend.service.errorHandling.ToDoNotFoundException;
import com.example.todo_list_backend.utils.JsonHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class ToDoController {

    private final ToDoService toDoService;

    private final ToDoRepository toDoRepository;

    @Autowired
    public ToDoController(ToDoService toDoService, ToDoRepository toDoRepository){
        this.toDoService = toDoService;
        this.toDoRepository = toDoRepository;
    }

    @GetMapping("")
    private ResponseEntity<String> toDos(@RequestParam Optional<Integer> pageNo) {
        int paramValue = pageNo.orElse(0);
        Page<ToDo> page = toDoRepository.toPage(5, paramValue);
        return new ResponseEntity<>(JsonHandler.toJson(page.getContent()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<String> getToDo(@PathVariable int id) {
        if(toDoRepository.existsById(id)) {
            return new ResponseEntity<>(JsonHandler.toJson(toDoRepository.findById(id)), HttpStatus.OK);
        }else {
            throw new ToDoNotFoundException(String.format("Couldn't find ToDo with ID: %d", id));
        }
    }

    @PutMapping("/{id}")
    private ResponseEntity<String> updateToDo(@PathVariable int id, @Valid @RequestBody ToDo newToDo) {
        if(toDoRepository.existsById(id)) {
            newToDo.setId(id);
            toDoService.edit(newToDo);
            return new ResponseEntity<>(JsonHandler.toJson(newToDo), HttpStatus.OK);
        }else {
            throw new ToDoNotFoundException(String.format("Couldn't find ToDo with ID: %d", id));
        }
    }

    @PostMapping("")
    private ResponseEntity<String> createToDo(@Valid @RequestBody ToDo toDo) {
        toDo.setId(toDoRepository.toDoMap.size());
        ToDo newToDo = toDoService.create(toDo);
        return new ResponseEntity<>(JsonHandler.toJson(newToDo), HttpStatus.CREATED);
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

    @DeleteMapping("{id}")
    private ResponseEntity<String> deleteToDo(@PathVariable int id){
        if(toDoRepository.existsById(id)) {
            toDoService.delete(id);
            return new ResponseEntity<>(JsonHandler.toJson(toDoRepository.findAll()), HttpStatus.OK);
        }else {
            throw new ToDoNotFoundException(String.format("Couldn't find ToDo with ID: %d", id));
        }
    }

}
