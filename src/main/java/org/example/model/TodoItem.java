package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class TodoItem {
    private String title;
    private String description;

    public TodoItem() {
        this.title = "";
        this.description = "";
    }
    public TodoItem(String title, String description) {
        this.title = title;
        this.description = description;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
