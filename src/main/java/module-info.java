module com.example.ludo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.sql;


    opens com.example.ludo to javafx.fxml;
    exports com.example.ludo;
}