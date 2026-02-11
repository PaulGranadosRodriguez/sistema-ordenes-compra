package com.sistema.ordenes.controller;
import com.sistema.ordenes.database.DatabaseConnection;
import com.sistema.ordenes.model.Product;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.chart.PieChart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DashboardController {
    
    @FXML private TableView <Product> tableProducts;
    @FXML private TableColumn <Product, Integer> colId;
    @FXML private TableColumn <Product,String> colName;
    @FXML private TableColumn <Product,Integer> colStock;
    @FXML private TableColumn <Product, Double> colPrice;

    @FXML private PieChart chartStock;

    @FXML
    public void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        loadDataFromDatabase();
    }

    private void loadDataFromDatabase(){
        ObservableList<Product> data=FXCollections.observableArrayList();
        String sql= "SELECT * FROM products";

        try (Connection conn = DatabaseConnection.getConnection();
            Statement stmt=conn.createStatement();
            ResultSet rs= stmt.executeQuery(sql)){
                while (rs.next()) {
                    data.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("stock"),
                        rs.getDouble("price")
                    ));
                }
                tableProducts.setItems(data);
                updatePiechart(data);
            
        } catch (Exception e) {
            System.err.println("Error al cargar datos: " + e.getMessage());

        }      
    }

    private void updatePiechart(ObservableList<Product> data){
        ObservableList<PieChart.Data> pieChartData= FXCollections.observableArrayList();
        for (Product p:data){
            pieChartData.add(new PieChart.Data(p.getName(),p.getStock()));
        }
        chartStock.setData(pieChartData);
        chartStock.setTitle("Distribución de inventario");
        chartStock.getData().forEach(piece->{
            String label=String.format("%s: %.0f uds", piece.getName(), piece.getPieValue());
            piece.setName(label);
        });
    }
}
