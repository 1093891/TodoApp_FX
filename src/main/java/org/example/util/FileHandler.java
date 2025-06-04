package org.example.util;

import org.example.model.TodoItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String TODO_FILE_PATH = "src/main/resources/todo.txt"; // Path to the todo file
    private static final String DELIMITER = ","; // Assuming comma as the delimiter for CSV format
    public String getTodoFilePath() {
        return TODO_FILE_PATH;
    }

    /**
     * Loads todos from a file.
     * @return List of TodoItem objects loaded from the file.
     */
    public static List<TodoItem> loadTodos() {
        File file = new File(TODO_FILE_PATH);
        if(!file.exists()) {
            // If the file does not exist, return an empty list
            return List.of();
        }
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            List<TodoItem> todos = new ArrayList<>();
           while((line =reader.readLine()) != null){
               String[] parts = line.split(DELIMITER);
               if(parts.length != 2) {
                   continue;
               }
               else{
                   String title = parts[0];
                     String description = parts[1];
                     TodoItem todoItem = new TodoItem(title, description);
                        todos.add(todoItem);
               }
           }
            return todos;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void saveTodos(List<TodoItem> todos) throws IOException {
        File file = new File(TODO_FILE_PATH);
        if(file.createNewFile()){
            System.out.println("File created: " + file.getName());
        } else {
            System.out.println("File already exists.");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))){
            if(todos.isEmpty()) {
                System.out.println("No todos to save.");
                return;
            }
            else{
                for(TodoItem todo : todos) {
                    String line = todo.getTitle() + DELIMITER + todo.getDescription();
                    System.out.println("Writing to file: " + line);
                    writer.write(line);
                    writer.newLine();
                }
            }
            System.out.println("Todos saved successfully.");

        }catch (IOException e){
            throw new RuntimeException("Error saving todos to file: " + e.getMessage(), e);
        }
    }

}
