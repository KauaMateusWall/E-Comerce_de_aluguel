package org.example.pi_primo.service;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.pi_primo.HelloApplication;
import org.example.pi_primo.config.ConexaoDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.pi_primo.config.ConexaoDB.conn;
import static org.example.pi_primo.config.ConexaoDB.showAlert;

public class TelaLogin {

    HelloApplication helloApplication = new HelloApplication();
    ConexaoDB helloController = new ConexaoDB();

    @FXML
    private TextField UsuarioTXT;
    @FXML
    private PasswordField SenhaTXT;



    @FXML
    public void onEntrarClicked(ActionEvent event) throws SQLException {
        String usuario = UsuarioTXT.getText();
        String senha = SenhaTXT.getText();

        try {
            helloController.conection();

            String query = "SELECT * FROM Cliente WHERE nome = ? AND senha = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                helloApplication.loadScreen("paginaMenu.fxml", "VK", event);

                showAlert("Login bem-sucedido", "Bem-vindo, " + usuario + "!", Alert.AlertType.ERROR);

            } else {
                showAlert("Falha no login", "Nome de usuário ou senha incorretos.", Alert.AlertType.ERROR);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            helloController.closeConection();
        }
    }


    @FXML
    public void omCadastroClicked(ActionEvent event) {
        helloApplication.loadScreen("paginaCadastro.fxml", "VK", event);
    }

    @FXML
    public void RecuperarSenha(ActionEvent event) {
        helloApplication.loadScreen("paginaRecuperarSenha.fxml", "Vk", event);
    }

}
