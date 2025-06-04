module org.example.todoapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.todoapp to javafx.fxml;
    exports org.example.todoapp;
    exports org.example.model;
    opens org.example.model to javafx.fxml;
}