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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// @Repository
// public interface ToDoRepository  extends JpaRepository<ToDo, Integer> {
public interface ToDoRepository {

    public Map<Integer, ToDo> toDoMap = generateSampleToDoMap();

    boolean existsById(int id);

    boolean existsByTextAndPriority(String text, String priority);

    ToDo findById(int id);

    ToDo findByTextAndPriority(String text, String priority);

    List<ToDo> findAllByText(String text);

    List<ToDo> findAllByPriority(String priority);

    List<ToDo> findAllByDone(boolean done);

    ToDo save(ToDo toDo);

    void deleteById(int id);

    static Map<Integer, ToDo> generateSampleToDoMap() {
        Map<Integer, ToDo> toDos = new HashMap<>();
        ToDo toDo1 = new ToDo("This is a to-do. Should be first on the list.", "LOW");
        ToDo toDo2 = new ToDo("This is another to-do. Should be second on the list.", "MEDIUM");
        ToDo toDo3 = new ToDo();
        ToDo toDo4 = new ToDo("This is yet another to-do. Should be fourth on the list.", "MEDIUM");

        toDos.put(toDo1.getId(), toDo1);
        toDos.put(toDo2.getId(), toDo2);
        toDos.put(toDo3.getId(), toDo3);
        toDos.put(toDo4.getId(), toDo4);
        return toDos;
    }
}
