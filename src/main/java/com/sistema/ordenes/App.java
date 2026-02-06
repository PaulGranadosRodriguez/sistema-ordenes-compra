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
        // Esta línea busca el archivo en la carpeta de resources donde está el menú
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sistema/ordenes/view/Menu.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        stage.setTitle("Sistema de Gestión - Menú Principal");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
