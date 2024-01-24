module oop.lab4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens oop.drawingApp to javafx.fxml;
    exports oop.drawingApp;
}