package org.example.pi_primo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.example.pi_primo.config.ConexaoDB;

import java.io.IOException;

public class HelloApplication extends Application {

    ConexaoDB conexaoDB = new ConexaoDB();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("paginaLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Empréstimo VK - Login");
        stage.setScene(scene);
        stage.show();
    }

    public void loadScreen(String fxmlFile, String title, Node main) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            Stage stage = (Stage) main.getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            ConexaoDB.showAlert("Erro", "Não foi possível carregar a página: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }


    public static void main(String[] args) {
        launch();
    }
}
