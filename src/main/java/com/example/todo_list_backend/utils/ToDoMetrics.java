package com.example.todo_list_backend.utils;

import com.example.todo_list_backend.model.Priority;
import com.example.todo_list_backend.model.ToDo;
import com.example.todo_list_backend.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
public class ToDoMetrics {

    private final ToDoRepository toDoRepository;

    @Autowired
    public ToDoMetrics(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    private List<ToDo> nullPurge() {
        List<ToDo> purgedList = new ArrayList<>(toDoRepository.findAll());
        purgedList.removeIf(toDo -> Objects.isNull(toDo.getDateDone()));
        return purgedList;
    }

    private List<ToDo> nullPurge(Priority priority) {
        List<ToDo> purgedList = new ArrayList<>(toDoRepository.findAllByPriority(priority));
        purgedList.removeIf(toDo -> Objects.isNull(toDo.getDateDone()));
        return purgedList;
    }

    private long averageDone(List<ToDo> toDoList) {
        if (toDoList.isEmpty()) {
            return 0;
        }
        long millis = 0;
        long size = 0;
        for (ToDo toDo : toDoList) {
            millis += Duration.between(toDo.getDateCreated(), toDo.getDateDone()).toMillis();
            size += 1;
        }
        return millis / size;
    }

    private long averageDoneMillis() {
        List<ToDo> toDoList = nullPurge();
        return averageDone(toDoList);
    }

    private long averageDoneMillis(Priority priority) {
        List<ToDo> toDoList = nullPurge(priority);
        return averageDone(toDoList);
    }


    public String metrics() {
        long millis = averageDoneMillis();
        return "Average time to finish tasks: \n"
                + String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
    }

    private String metrics(Priority priority) {
        long millis = averageDoneMillis(priority);
        return priority.description() + ": "
                + String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
    }

    public String metricsByPriority() {
        return "Average time to finish tasks by priority: \n"
                + metrics(Priority.LOW)
                + "\n" + metrics(Priority.MEDIUM)
                + "\n" + metrics(Priority.HIGH);
    }


}
