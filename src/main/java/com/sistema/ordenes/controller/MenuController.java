package com.sistema.ordenes.controller;

import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;

public class MenuController {


    @FXML
    private void handleDashboard(javafx.event.ActionEvent event){
        try{
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/com/sistema/ordenes/view/Dashboard.fxml"));
            Parent root=loader.load();

            

            Stage menuStage=(Stage)((Node) event.getSource()).getScene().getWindow();
            menuStage.hide();

            Stage dashboardStage= new Stage();
            dashboardStage.setScene(new Scene(root));
            dashboardStage.setTitle("Dashboard-Sistema de Gestion");
            
            dashboardStage.setOnCloseRequest(e->menuStage.show());
            
            dashboardStage.show();

        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Error al cargar el dashboard");
        }
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
