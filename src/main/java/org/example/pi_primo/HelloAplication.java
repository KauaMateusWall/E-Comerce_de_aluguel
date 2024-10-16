package org.example.pi_primo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloAplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Carregar o arquivo FXML
        FXMLLoader fxmlLoader = new FXMLLoader(HelloAplication.class.getResource("paginaCadastroProduto.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);  // Carregando o layout da cena
        stage.setTitle("Empréstimo VK - Login");  // Definindo o título da janela
        stage.setScene(scene);  // Definindo a cena no palco
        stage.show();  // Exibindo o palco
    }

    public static void main(String[] args) {
        launch();  // Iniciando a aplicação
    }
}
