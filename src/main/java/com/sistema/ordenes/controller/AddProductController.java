package com.sistema.ordenes.controller;

import com.sistema.ordenes.database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;


public class AddProductController {
    @FXML private TextField txtName;
    @FXML private TextField txtStock;
    @FXML private TextField txtPrice;
    @FXML private ComboBox<String>cmbBrand;

    @FXML
    public void initialize(){
        loadBrands();
    }

    private void loadBrands() {
        ObservableList<String> brands= FXCollections.observableArrayList();
        String sql= "SELECT name FROM brands";

        try (Connection conn= DatabaseConnection.getConnection();
            Statement stmt= conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            
            while (rs.next()) {
                brands.add(rs.getString("name"));
            }
            cmbBrand.setItems(brands);
            
        } catch (SQLException e) {
            System.err.println("Error al cargar marcas"+ e.getMessage());
        }
    }

    @FXML
    private void saveProduct(){
        String name= txtName.getText();
        String stock= txtStock.getText();
        String price= txtPrice.getText();
        String brandName= cmbBrand.getValue();

        if(name.isEmpty() || stock.isEmpty() || price.isEmpty()|| brandName == null){
            showAlert("Error","Favor rellenar todos los campos.");
            return;
        }

        int brandId= getBrandId(brandName);
        if(brandId== -1){
            showAlert("Error", "No se pudo encontrar el ID de la marca.");
            return;
        }

        String sql="INSERT INTO products (name, stock, price, brand_id) VALUES (?,?,?,?)";

        try (Connection conn= DatabaseConnection.getConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql)){
            pstmt.setString(1, name);
            pstmt.setInt(2, Integer.parseInt(stock));
            pstmt.setDouble(3, Double.parseDouble(price));
            pstmt.setInt(4, brandId);

            pstmt.executeUpdate();

            Stage stage= (Stage) txtName.getScene().getWindow();
            stage.close();
            
        } catch (NumberFormatException e) {
            showAlert("Error de formato", "Stock y precio deben ser números");
        }catch (SQLException e){
            System.err.println("Error al insertar: "+ e.getMessage());
        }
    }

    private int getBrandId(String brandName){
        String sql="SELECT id FROM brands WHERE name=?";
        try(Connection conn= DatabaseConnection.getConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql)){
            pstmt.setString(1, brandName);
            ResultSet rs=pstmt.executeQuery();
            if(rs.next()){
                return rs.getInt("id");
            }
        }catch(SQLException e){
            System.err.println("Error al obtener ID de marca "+ e.getMessage());
            } 
        
        return -1;
    }
        @FXML
        private void openAddBrandWindow(){
            System.out.println("Abriendo ventana para añadir nueva marca");
        }

    private void showAlert(String title, String content) {
        Alert alert= new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
}
