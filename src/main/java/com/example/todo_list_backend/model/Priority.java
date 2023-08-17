package com.example.todo_list_backend.model;

import java.awt.*;

public enum Priority {
    LOW("Low", Color.GREEN),
    MEDIUM("Medium", Color.YELLOW),
    HIGH("High", Color.RED);

    private final String description;
    private final Color color;

    Priority(String description, Color color) {
        this.description = description;
        this.color = color;
    }

    public String description() {
        return description;
    }

    public Color color() {
        return color;
    }

}
