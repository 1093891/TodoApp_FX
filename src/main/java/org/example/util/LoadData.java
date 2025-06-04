package org.example.util;

import org.example.model.TodoItem;
import org.example.model.TodoList;

import java.util.List;

public class LoadData {
    private TodoList todoList;

    public LoadData(TodoList todolist) {
        this.todoList = todolist;
    }
    public void loadData() {
        System.out.println("Loading data...");
        List<TodoItem> todos = FileHandler.loadTodos();

        if (todos.isEmpty()) {
            System.out.println("No todos found.");
        } else {
            System.out.println("Todos loaded successfully:");
            System.out.println(todos.size()+" todo(s) found.");
            for(TodoItem item : todos) {
                todoList.addItem(item);
            }
        }
    }

    public void saveData() {

        try {
            FileHandler.saveTodos(todoList.getItems());

        } catch (Exception e) {
            System.err.println("Error saving todos: " + e.getMessage());
        }
    }
}
