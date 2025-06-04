module org.example.todoapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.todoapp to javafx.fxml;
    exports org.example.todoapp;
}