package org.example.pi_primo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.pi_primo.config.ConexaoDB;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {

    ConexaoDB conexaoDB = new ConexaoDB();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("paginalogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Empréstimo VK - Login");
        stage.setScene(scene);
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


    public void loadScreenMouse(String fxml, String title, MouseEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}
