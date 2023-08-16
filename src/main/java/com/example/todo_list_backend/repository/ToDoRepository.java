package com.example.todo_list_backend.repository;

/*
    When database is ready, uncomment the @Repository tag and
    make this interface extend JpaRepository<ToDo, Integer>.
    Make sure to include the necessary imports and add the
    corresponding dependency in the pom.
 */

import com.example.todo_list_backend.model.Priority;
import com.example.todo_list_backend.model.ToDo;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


// @Repository
// public interface ToDoRepository  extends JpaRepository<ToDo, Integer> {
public interface ToDoRepository {

    public Map<Integer, ToDo> toDoMap = generateSampleToDoMap();

    boolean existsById(int id);

    boolean existsByTextAndPriority(String text, Priority priority);

    ToDo findById(int id);

    ToDo findByTextAndPriority(String text, Priority priority);

    List<ToDo> findAll();

    List<ToDo> findAllByText(String text);

    List<ToDo> findAllByPriority(Priority priority);

    List<ToDo> findAllByDone(boolean done);

    ToDo save(ToDo toDo);

    void deleteById(int id);

    static Map<Integer, ToDo> generateSampleToDoMap() {
        Map<Integer, ToDo> toDos = new LinkedHashMap<>();

        ToDo toDo1 = new ToDo(0, "This is a to-do. Should be first on the list.", Priority.LOW);
        toDos.put(toDo1.getId(), toDo1);

        ToDo toDo2 = new ToDo(toDos.size(), "This is another to-do. Should be second on the list.",
                Priority.MEDIUM, LocalDate.parse("2023-10-15"));
        toDos.put(toDo2.getId(), toDo2);

        ToDo toDo3 = new ToDo(toDos.size(), "This is yet another to-do. Should be third on the list.",
                Priority.HIGH);
        toDos.put(toDo3.getId(), toDo3);

        ToDo toDo4 = new ToDo(toDos.size(), "This is one more to-do. Should be fourth on the list.", Priority.HIGH,
                LocalDate.parse("2023-08-10"));
        toDos.put(toDo4.getId(), toDo4);

        return toDos;
    }
}
