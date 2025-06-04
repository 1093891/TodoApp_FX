package org.example.todoapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.model.TodoList;
import org.example.util.LoadData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    private TodoList sharedTodoList;
    private LoadData loadData;
    @Override
    public void start(Stage stage) {
        sharedTodoList = new TodoList();
        loadData = new LoadData(sharedTodoList);
        TodoUI todoUI = new TodoUI(loadData, sharedTodoList);
        Scene scene = new Scene(todoUI.getView(), 500, 500);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.onCloseRequestProperty().set(e -> {
          loadData.saveData();
            try {
                System.out.println("the application is closing, saving data...");
                Thread.sleep(1000);

            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            System.exit(0);});
        stage.setResizable(false);
        stage.setTitle("Todo Application");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
