package org.example.pi_primo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.pi_primo.config.ConexaoDB;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {

    ConexaoDB conexaoDB = new ConexaoDB();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("paginalogin.fxml"));
        Scene scene = fxmlLoader.load();
        stage.setTitle("Empréstimo VK - Login");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.show();
    }

    public void loadScreen(String fxmlFile, String title, Scene sceneOriginal) {
        try {
            Scene sceneNova = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
            Stage stage = new Stage();
            stage.setScene(sceneNova);
            stage.setTitle(title);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args) {
        launch();
    }
}

