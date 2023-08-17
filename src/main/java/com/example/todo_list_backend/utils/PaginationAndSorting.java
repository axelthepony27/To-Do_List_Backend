package com.example.todo_list_backend.utils;

import com.example.todo_list_backend.model.ToDo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Comparator;
import java.util.List;

public abstract class PaginationAndSorting {

    public static Page<ToDo> toDoListToPage(List<ToDo> list, int pageSize, int pageNo, String sortType, boolean descending) {
        switch (sortType) {
            case "priority" -> {
                if (descending) {
                    list.sort(Comparator.comparing(ToDo::getPriority).reversed());
                } else {
                    list.sort(Comparator.comparing(ToDo::getPriority));
                }
            }
            case "dueDate" -> {
                if (descending) {
                    list.sort(Comparator.comparing(ToDo::getDueDate, Comparator.nullsFirst(Comparator.naturalOrder())).reversed());
                } else {
                    list.sort(Comparator.comparing(ToDo::getDueDate, Comparator.nullsFirst(Comparator.naturalOrder())));
                }
            }
            case "priorityAndDueDate" -> {
                if (descending) {
                    list.sort(Comparator.comparing(ToDo::getDueDate, Comparator.nullsFirst(Comparator.naturalOrder())).reversed());
                    list.sort(Comparator.comparing(ToDo::getPriority).reversed());
                } else {
                    list.sort(Comparator.comparing(ToDo::getDueDate, Comparator.nullsFirst(Comparator.naturalOrder())));
                    list.sort(Comparator.comparing(ToDo::getPriority));
                }
            }
        }


        int totalPages = list.size() / pageSize;
        PageRequest pageable = PageRequest.of(pageNo, pageSize);

        int max = pageNo >= totalPages ? list.size() : pageSize * (pageNo + 1);
        int min = pageNo > totalPages ? max : pageSize * pageNo;

        return new PageImpl<>(list.subList(min, max), pageable, list.size());
    }

}
