package org.example.pi_primo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import org.springframework.mail.SimpleMailMessage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.time.LocalDate;

public class HelloController {

    private static final String URL = "jdbc:mysql://localhost:3306/PI_Primo";
    private static final String USER = "root";
    private static final String PASSWORD = "0000";
    public static Connection conn;

    public void conection() throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void closeConection() throws SQLException {
        conn.close();
    }

    public static void showAlert(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}



