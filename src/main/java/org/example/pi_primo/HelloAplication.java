package org.example.pi_primo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;

public class HelloAplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Carregar o arquivo FXML
        FXMLLoader fxmlLoader = new FXMLLoader(HelloAplication.class.getResource("paginaCadastroProduto.fxml"));
        Scene scene = new Scene(fxmlLoader.load());  // Carregando o layout da cena
        stage.setTitle("Empréstimo VK - Login");  // Definindo o título da janela
        stage.setScene(scene);  // Definindo a cena no palco
        stage.show();
    }

    public void loadScreen(String fxmlFile, String title, javafx.event.ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();  // Iniciando a aplicação
    }
}
