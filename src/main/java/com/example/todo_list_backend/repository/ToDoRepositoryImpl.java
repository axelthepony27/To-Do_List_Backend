package com.example.todo_list_backend.repository;

import com.example.todo_list_backend.model.Priority;
import com.example.todo_list_backend.model.ToDo;
import com.example.todo_list_backend.service.ToDoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
    Once the database is ready, this class won't be needed anymore.
    Make sure to safe delete and refactor all that's necessary.
 */
@Component
public class ToDoRepositoryImpl implements ToDoRepository{

    public List<ToDo> toDoList = generateSampleToDoList();

    @Override
    public boolean existsByTextAndPriority(String text, Priority priority) {
        ToDo newToDo = new ToDo(text, priority);
        return this.toDoList.contains(newToDo);
    }

    @Override
    public ToDo findByTextAndPriority(String text, Priority priority) {
        if(existsByTextAndPriority(text, priority)){
            ToDo newToDo = new ToDo(text, priority);
            return this.toDoList.get(this.toDoList.indexOf(newToDo));
        } else{
            return null;
        }
    }

    @Override
    public List<ToDo> findAllByText(String text) {
        List<ToDo> newList = new ArrayList<>();
        for(ToDo toDo : this.toDoList){
            if(toDo.getText().equals(text)){
                newList.add(toDo);
            }
        }

        return newList;
    }

    @Override
    public List<ToDo> findAllByPriority(Priority priority) {
        List<ToDo> newList = new ArrayList<>();
        for(ToDo toDo : this.toDoList){
            if(toDo.getPriority() == priority){
                newList.add(toDo);
            }
        }

        return newList;
    }

    @Override
    public List<ToDo> findAllByDone(boolean done) {
        List<ToDo> newList = new ArrayList<>();
        for(ToDo toDo : this.toDoList){
            if(toDo.isDone() == done){
                newList.add(toDo);
            }
        }

        return newList;
    }

    public List<ToDo> generateSampleToDoList(){
        List<ToDo> toDos = new ArrayList<>();
        toDos.add(new ToDo("This is a to-do. Should be first on the list.", Priority.LOW));
        toDos.add(new ToDo("This is another to-do. Should be second on the list.", Priority.MEDIUM));
        toDos.add(new ToDo());
        toDos.add(new ToDo("This is one more to-do. Should be fourth on the list.", Priority.HIGH));
        return toDos;
    }
}
