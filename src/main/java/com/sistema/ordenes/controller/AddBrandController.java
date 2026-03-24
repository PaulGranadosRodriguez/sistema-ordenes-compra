package com.sistema.ordenes.controller;

import com.sistema.ordenes.database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class AddBrandController {
    @FXML private TextField txtBrandName;

    @FXML
    private void saveBrand(){
        String name= txtBrandName.getText().trim();

        if (name.isEmpty()){
            showAlert("Error","El nombre de la marca no puede estar vacío");
            return;
            
        }

        String sql= "INSERT INTO brands (name) VALUES (?)";

        try (Connection conn= DatabaseConnection.getConnection();
            PreparedStatement pstmt= conn.prepareStatement(sql)){
            pstmt.setString(1, name);
            pstmt.executeUpdate();

            Stage stage=(Stage) txtBrandName.getScene().getWindow();
            stage.close();
            
        } catch (SQLException e) {
            if(e.getMessage().contains("UNIQUE")){
                showAlert("Error", "Esta marca ya esta registrada.");
            } else {
                System.err.println("Error la insertar marca: "+ e.getMessage());
            }
        }

    }
        private void showAlert(String title,String content){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);
            alert.showAndWait();

        }


}
