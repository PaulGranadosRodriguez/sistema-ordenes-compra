module com.sistema.ordenes {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.sistema.ordenes to javafx.fxml; //Paquetes para que cargue los FXML
    opens com.sistema.ordenes.controller to javafx.fxml;
    opens com.sistema.ordenes.model to javafx.fxml;
    
    exports com.sistema.ordenes;
    exports com.sistema.ordenes.controller;
    exports com.sistema.ordenes.model;

}