module com.sistema.ordenes {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.sistema.ordenes to javafx.fxml;
    exports com.sistema.ordenes;
}