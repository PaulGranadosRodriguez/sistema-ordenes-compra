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

            Stage currentStage=(Stage)((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            Stage newStage= new Stage();
            newStage.setScene(new Scene(root));
            newStage.setTitle("Dashboard-Sistema de Gestion");
            newStage.show();

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
