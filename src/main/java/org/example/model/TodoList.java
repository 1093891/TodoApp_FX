package org.example.model;

import java.util.List;

public class TodoList {
    private List<TodoItem> items;
    public TodoList() {
        this.items = new java.util.ArrayList<>();
    }

    public TodoList(String title, String description) {
        TodoItem todoItem = new TodoItem(title, description);
        this.items = new java.util.ArrayList<>();
        addItem(todoItem);
    }
    public List<TodoItem> getItems() {
        return items;
    }

    public void addItem(TodoItem item) {
        if (items == null) {
            items = new java.util.ArrayList<>();
        }
        items.add(item);
    }
    public void removeItem(TodoItem item) {
        if (items != null) {
            items.remove(item);
        }
    }
}
