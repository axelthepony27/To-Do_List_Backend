package com.example.todo_list_backend.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/*
    When database is ready, make sure to annotate this class
    with @Entity and @Table, and assign the corresponding
    columns. Also, ID should be autogenerated in ascending order.
 */
// @JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ToDo {

    private int id;
    @NotBlank(message = "The text for the to-do is required.")
    @Size(max = 120)
    private String text;
    private boolean done;
    @NotNull
    private Priority priority;
    private LocalDate dueDate;
    private final LocalDateTime dateCreated = LocalDateTime.now();
    private LocalDateTime dateDone;

    public ToDo() {
    }

    public ToDo(int id, String text, Priority priority) {
        this.id = id;
        this.text = text;
        this.done = false;
        this.priority = priority;
        this.dueDate = null;
        this.dateDone = null;
    }

    public ToDo(int id, String text, Priority priority, LocalDate dueDate) {
        this.id = id;
        this.text = text;
        this.done = false;
        this.priority = priority;
        this.dueDate = dueDate;
        this.dateDone = null;
    }

    public ToDo(String text, Priority priority, LocalDate dueDate) {
        this(0, text, priority, dueDate);
    }

    public ToDo(String text, Priority priority) {
        this(0, text, priority);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public LocalDateTime getDateDone() {
        return dateDone;
    }

    public void setDateDone(LocalDateTime dateDone) {
        this.dateDone = dateDone;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", done=" + done +
                ", priority=" + priority +
                ", dueDate=" + dueDate +
                ", dateCreated=" + dateCreated.toLocalDate() +
                ", dateDone=" + dateDone.toLocalDate() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDo toDo = (ToDo) o;
        return Objects.equals(text, toDo.text) && priority.equals(toDo.priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, priority);
    }
}
