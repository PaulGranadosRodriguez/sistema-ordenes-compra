package com.sistema.ordenes.controller;

import com.sistema.ordenes.database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class AddProductController {
    @FXML private TextField txtName;
    @FXML private TextField txtStock;
    @FXML private TextField txtPrice;

    @FXML
    private void saveProduct(){
        String name= txtName.getText();
        String stock= txtStock.getText();
        String price= txtPrice.getText();

        if(name.isEmpty() || stock.isEmpty() || price.isEmpty()){
            showAlert("Error","Favor rellenar todos los campos.");
            return;
        }
        String sql="INSERT INTO products (name, stock, price) VALUES (?,?,?)";

        try (Connection conn= DatabaseConnection.getConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql)){
            pstmt.setString(1, name);
            pstmt.setInt(2, Integer.parseInt(stock));
            pstmt.setDouble(3, Double.parseDouble(price));

            pstmt.executeUpdate();

            Stage stage= (Stage) txtName.getScene().getWindow();
            stage.close();
            
        } catch (NumberFormatException e) {
            showAlert("Error de formato", "Stock y precio deben ser números");
        }catch (SQLException e){
            System.err.println("Error al insertar: "+ e.getMessage());
        }
    }

    private void showAlert(String title, String content) {
        Alert alert= new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
}
