package org.example.todoapp;

import javafx.application.Application;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import org.example.model.*;
import org.example.util.LoadData;



public class TodoUI extends Application {
    private LoadData loadData;
    private BorderPane borderPane;
    private TodoList todoList;


    public TodoUI( LoadData loadData, TodoList todoList) {
        this.loadData = loadData;
        this.todoList = todoList;
        init();

    }
    public void init(){
        System.out.println("Initializing TodoUI...");
        loadData.loadData();

        borderPane = new BorderPane();

        Label title = new Label("Todo List");
        title.setStyle("-fx-font-size: 24px; -fx-text-fill: #333;");
        borderPane.setTop(title);

        VBox todoListPane = new VBox();

        ScrollPane scrollPane = new ScrollPane();
        ListView<String> todoListView = new ListView<>();
        todoListView.setStyle("-fx-font-size: 16px; -fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-width: 1px;");
        todoListView.setPrefSize(480, 480);

        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.setStyle("-fx-padding: 10; -fx-background-color: #f0f0f0;");
        TextField todoTitle = new TextField();
        todoTitle.setPromptText("Enter todo item title");
        todoTitle.setStyle("-fx-font-size: 16px; -fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-width: 1px;");

        TextArea todoDescription = new TextArea();
        todoDescription.setPromptText("Enter todo item description");
        todoDescription.setStyle("-fx-font-size: 16px; -fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-width: 1px;");
        todoDescription.setPrefHeight(60);
        Button addButton = new Button("Add Todo");
        addButton.setStyle("-fx-font-size: 16px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 5 10;");
        addButton.setOnAction(e -> {
            String titleText = todoTitle.getText();
            String descriptionText = todoDescription.getText();
            if (!titleText.isEmpty() && !descriptionText.isEmpty()) {
                TodoItem todoItem = new TodoItem(titleText, descriptionText);
                this.todoList.addItem(todoItem);
                todoListView.getItems().add(todoItem.getTitle() + ": " + todoItem.getDescription());
                todoTitle.clear();
                todoDescription.clear();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill in both fields.");
                alert.showAndWait();
            }
        });
        Button clearButton = new Button("Clear");
        clearButton.setStyle("-fx-font-size: 16px; -fx-background-color: #f44336; -fx-text-fill: white; -fx-padding: 5 10;");
        clearButton.setOnAction(e -> {
            todoTitle.clear();
            todoDescription.clear();
        });


        hbox.getChildren().addAll(new VBox(10,new HBox(15,todoTitle, addButton, clearButton), todoDescription));
        scrollPane.setContent(todoListView);


        for(TodoItem item : todoList.getItems()) {
            todoListView.getItems().add(item.getTitle() + ": " + item.getDescription());
        }

        todoListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selectedItem = todoListView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    String[] parts = selectedItem.split(": ");
                    if (parts.length == 2) {
                        todoTitle.setText(parts[0]);
                        todoDescription.setText(parts[1]);
                        Alert confirmDelete = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to edit this todo item?");
                        confirmDelete.setTitle("Edit Todo Item");
                        confirmDelete.setHeaderText(null);
                        confirmDelete.showAndWait().ifPresent(response -> {
                            if (response == ButtonType.OK) {
                                TodoItem itemToRemove = new TodoItem(parts[0], parts[1]);
                                todoList.removeItem(itemToRemove);
                                todoListView.getItems().remove(selectedItem);
                                loadData.saveData();
                            }
                        });
                    }
                }
            }
        });


        todoListPane.getChildren().add(hbox);
        todoListPane.getChildren().add(scrollPane);
        borderPane.setCenter(todoListPane);
        borderPane.setPrefSize(400, 500);
        borderPane.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 10;");








  }
    @Override
    public void start(Stage primaryStage) {

    }

    public BorderPane getView(){
        return borderPane;
    }

}
