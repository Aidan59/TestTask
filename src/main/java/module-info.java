module org.example.testtask {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.testtask to javafx.fxml;
    exports org.example.testtask;
}