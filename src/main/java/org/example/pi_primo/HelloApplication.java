package org.example.pi_primo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("paginaLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Empréstimo VK - Login");
        stage.setScene(scene);
        stage.show();

        // Sending email in a new thread
        new Thread(this::sendEmail).start();
    }

    private void sendEmail() {
        String meuEmail = "vk.alugueis@gmail.com";
        String minhaSenha = "VK-99088"; // Consider using a safer method to store credentials

        SimpleEmail email = new SimpleEmail();
        email.setHostName("smtp.gmail.com"); // Corrected SMTP host
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(meuEmail, minhaSenha));
        email.setSSLOnConnect(true);

        try {
            email.setFrom(meuEmail);
            email.setSubject("Título");
            email.setMsg("Conteúdo");
            email.addBcc(meuEmail);
            email.send();
            System.out.println("Email enviado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace(); // Log the error
        }
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
        launch();
    }
}
