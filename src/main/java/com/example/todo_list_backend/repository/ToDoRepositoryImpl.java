package com.example.todo_list_backend.repository;

import com.example.todo_list_backend.model.Priority;
import com.example.todo_list_backend.model.ToDo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.Map.Entry;

/*
    Once the database is ready, this class won't be needed anymore.
    Make sure to safe delete and refactor all that's necessary.
 */
@Component
public class ToDoRepositoryImpl implements ToDoRepository{

    @Override
    public boolean existsById(int id){
        return toDoMap.containsKey(id);
    }

    @Override
    public ToDo findById(int id){
        return existsById(id) ? toDoMap.get(id) : null;
    }

    @Override
    public boolean existsByTextAndPriority(String text, Priority priority) {
        return toDoMap.containsValue(new ToDo(text, priority));
    }

    @Override
    public ToDo findByTextAndPriority(String text, Priority priority) {
        if(existsByTextAndPriority(text, priority)){
            ToDo value = new ToDo(text, priority);
            for (Entry<Integer, ToDo> entry : toDoMap.entrySet()) {
                if (Objects.equals(value, entry.getValue())) {
                    return toDoMap.get(entry.getKey());
                }
            }
        }
        return null;
    }

   @Override
   public List<ToDo> findAll(){
       return new ArrayList<>(this.toDoMap.values());
   }

    @Override
    public List<ToDo> findAllByText(String text) {
        List<ToDo> newList = new ArrayList<>();
        for(ToDo toDo : this.toDoMap.values()){
            if(toDo.getText().equals(text)){
                newList.add(toDo);
            }
        }

        return newList;
    }

    @Override
    public List<ToDo> findAllByPriority(Priority priority) {
        List<ToDo> newList = new ArrayList<>();
        for(ToDo toDo : this.toDoMap.values()){
            if(toDo.getPriority().equals(priority)){
                newList.add(toDo);
            }
        }

        return newList;
    }

    @Override
    public List<ToDo> findAllByDone(boolean done) {
        List<ToDo> newList = new ArrayList<>();
        for(ToDo toDo : this.toDoMap.values()){
            if(toDo.isDone() == done){
                newList.add(toDo);
            }
        }

        return newList;
    }

    @Override
    public ToDo save(ToDo toDo){
        this.toDoMap.put(toDo.getId(), toDo);
        return toDo;
    }

    @Override
    public void deleteById(int id){
        this.toDoMap.remove(id);
    }

    @Override
    public Page<ToDo> toPage(int pageSize, int pageNo){
        List<ToDo> list = this.findAll();

        int totalPages = list.size() / pageSize;
        PageRequest pageable = PageRequest.of(pageNo, pageSize);

        int max = pageNo >= totalPages ? list.size() : pageSize * (pageNo + 1);
        int min = pageNo > totalPages ? max : pageSize * pageNo;

        // logger.info("totalPages{} pageSize {} pageNo {}   list size {} min {}   max {} ...........", totalPages,pageSize, pageNo, list.size(), min, max);

        return new PageImpl<>(list.subList(min, max), pageable, list.size());
    }

}
