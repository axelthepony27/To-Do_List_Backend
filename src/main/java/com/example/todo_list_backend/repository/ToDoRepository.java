package com.example.todo_list_backend.repository;

/*
    When database is ready, uncomment the @Repository tag and
    make this interface extend JpaRepository<ToDo, Integer>.
    Make sure to include the necessary imports and add the
    corresponding dependency in the pom.
 */

import com.example.todo_list_backend.model.Priority;
import com.example.todo_list_backend.model.ToDo;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


// @Repository
// public interface ToDoRepository  extends JpaRepository<ToDo, Integer> {
public interface ToDoRepository {
    boolean existsById(int id);

    boolean existsByTextAndPriority(String text, Priority priority);

    ToDo findById(int id);

    ToDo findByTextAndPriority(String text, Priority priority);

    List<ToDo> findAllByText(String text);

    List<ToDo> findAllByPriority(Priority priority);

    List<ToDo> findAllByDone(boolean done);

    ToDo save(ToDo toDo);

    void deleteById(int id);
}
