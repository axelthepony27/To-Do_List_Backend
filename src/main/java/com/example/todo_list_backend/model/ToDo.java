package com.example.todo_list_backend.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;

/*
    When database is ready, make sure to annotate this class
    with @Entity and @Table, and assign the corresponding
    columns. Also, ID should be autogenerated in ascending order.
 */
// @JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ToDo {

    private int id = new Random().nextInt(100000);
    @NotBlank(message = "The text for the to-do is required.")
    @Size(max = 120)
    private String text;
    private boolean done;
    private Priority priority;
    private LocalDate dueDate;
    private LocalDate dateCreated;
    private LocalDate dateDone;

    public ToDo() {
    }

    public ToDo(String text, Priority priority) {
        this.text = text;
        this.done = false;
        this.priority = priority;
        this.dueDate = null;
        this.dateCreated = java.time.LocalDate.now();
        this.dateDone = null;
    }

    public ToDo(String text, Priority priority, LocalDate dueDate) {
        this.text = text;
        this.done = false;
        this.priority = priority;
        this.dueDate = dueDate;
        this.dateCreated = java.time.LocalDate.now();
        this.dateDone = null;
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

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public LocalDate getDateDone() {
        return dateDone;
    }

    public void setDateDone(LocalDate dateDone) {
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
                ", dateCreated=" + dateCreated +
                ", dateDone=" + dateDone +
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
