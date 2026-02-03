package com.sistema.ordenes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;



public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Esta línea busca el archivo en la carpeta de resources que acabamos de crear
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sistema/ordenes/Dashboard.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        stage.setTitle("Sistema de Órdenes - Dashboard Profesional");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
