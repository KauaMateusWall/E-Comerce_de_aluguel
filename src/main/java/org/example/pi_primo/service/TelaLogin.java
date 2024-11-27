package org.example.pi_primo.service;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.pi_primo.HelloApplication;
import org.example.pi_primo.config.ConexaoDB;
import org.example.pi_primo.model.Cliente;
import org.example.pi_primo.model.Session;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.pi_primo.config.ConexaoDB.conn;
import static org.example.pi_primo.config.ConexaoDB.showAlert;

public class TelaLogin {

    public Scene mainScene;
    HelloApplication helloApplication = new HelloApplication();
    ConexaoDB helloController = new ConexaoDB();

    @FXML
    private TextField UsuarioTXT;

    @FXML
    private PasswordField SenhaTXT;

    @FXML
    private TextField SenhaVisivelTXT;

    @FXML
    private CheckBox CheckBox;

    @FXML
    public void initialize() {
        SenhaVisivelTXT.setVisible(false);
        SenhaVisivelTXT.managedProperty().bind(SenhaVisivelTXT.visibleProperty());
    }

    @FXML
    public void mostrarSenha(MouseEvent mouseEvent) {
        if (CheckBox.isSelected()) {
            SenhaVisivelTXT.setText(SenhaTXT.getText());
            SenhaVisivelTXT.setVisible(true);
            SenhaTXT.setVisible(false);
        } else {
            SenhaTXT.setText(SenhaVisivelTXT.getText());
            SenhaTXT.setVisible(true);
            SenhaVisivelTXT.setVisible(false);
        }
    }

    @FXML
    public void onEntrarClicked(ActionEvent event) throws SQLException {
        String usuario = UsuarioTXT.getText();
        String senha = SenhaTXT.isVisible() ? SenhaTXT.getText() : SenhaVisivelTXT.getText();

        try {
            helloController.conection();

            String query = "SELECT * FROM Cliente WHERE nome = ? AND senha = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("CPF"),
                        rs.getString("email"),
                        rs.getString("endereco"),
                        rs.getString("telefone"),
                        rs.getString("data_nascimento")
                );

                Session.usuario.setNome(cliente.getNome());
                Session.usuario.setCPF(cliente.getCPF());
                Session.usuario.setid(cliente.getid());
                Session.usuario.setEmail(cliente.getEmail());
                Session.usuario.setEndereco(cliente.getEndereco());
                Session.usuario.setNascimento(cliente.getNascimento());
                Session.usuario.setTelefone(cliente.getTelefone());

                Stage currentStage = (Stage) mainScene.getWindow();
                currentStage.close();

                helloApplication.openScreen("paginaMenu.fxml", "VK", mainScene);

                showAlert("Login bem-sucedido", "Bem-vindo, " + usuario + "!", Alert.AlertType.INFORMATION);

            } else {
                showAlert("Falha no login", "Nome de usuário ou senha incorretos.", Alert.AlertType.ERROR);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void omCadastroClicked(ActionEvent event) {
        helloApplication.loadScreen("paginaCadastro.fxml", "VK", mainScene);
    }
}
