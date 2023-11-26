module com.example.datastructureprojectone {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.datastructureprojectone to javafx.fxml;
    exports com.example.datastructureprojectone;
}