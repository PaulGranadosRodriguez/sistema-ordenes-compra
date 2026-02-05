package com.sistema.ordenes;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.chart.PieChart;

import java.security.PrivateKey;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
        
        //Lista de prueba
        ObservableList<Product>data = FXCollections.observableArrayList(
            new Product(1,"Laptop", 10, 1500.0),
            new Product(2, "Mouse", 50, 25.0),
            new Product(3,"Teclado", 30 , 45.0)
        );

        tableProducts.setItems(data);

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for(Product p:data){
            pieChartData.add(new PieChart.Data(p.getName(),p.getStock()));
        }
        chartStock.setData(pieChartData);
        chartStock.setTitle("Distribución de inventario");

        chartStock.getData().forEach(dataPiece->{
            String label= String.format("%s:%.0f", dataPiece.getName(),dataPiece.getPieValue());
            dataPiece.setName(label);
        });

        chartStock.setLabelsVisible(true);
        chartStock.setLabelLineLength(20);
    }

}
