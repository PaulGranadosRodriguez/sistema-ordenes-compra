package com.sistema.ordenes.controller;

import javafx.fxml.FXML;
import javafx.application.Platform;



public class MenuController {
    @FXML
    private void handleDashboard(){
        System.out.print("Navegando el dashboard..");
    }
    @FXML
    private void handleAddProduct(){
        System.out.print("Abriendo ventana de gestión productos...");
    }

    @FXML
    private void handleExit(){
        System.out.print("Cerrando Sistema");
        Platform.exit();
    }
}
