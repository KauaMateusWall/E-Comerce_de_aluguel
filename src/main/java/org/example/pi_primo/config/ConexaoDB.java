package org.example.pi_primo.config;

import javafx.scene.control.*;

import java.sql.*;

public class ConexaoDB {

    private static final String URL = "jdbc:mysql://localhost:3306/PI_Primo";
    private static final String USER = "root";
    private static final String PASSWORD = "0000";
    public static Connection conn;

    public void conection() throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
    }


    public static void showAlert(String titulo, String mensagem, Alert.AlertType error) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}



