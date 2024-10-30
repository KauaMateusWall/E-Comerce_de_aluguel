package org.example.pi_primo.service;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;
import org.example.pi_primo.HelloApplication;
import org.example.pi_primo.config.ConexaoDB;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.pi_primo.config.ConexaoDB.conn;
import static org.example.pi_primo.config.ConexaoDB.showAlert;

public class ServicoEmail {

    @FXML
    private TextField emailText;
    @FXML
    public TextField usuarioText;
    @FXML
    public Button enviarButton;
    @FXML
    public Button voltarButton;

    private final HelloApplication helloApplication;
    private final ConexaoDB helloController;

    @Autowired
    public ServicoEmail(HelloApplication helloApplication, ConexaoDB helloController) {
        this.helloApplication = helloApplication;
        this.helloController = helloController;
    }

    public void enviarSenhaPorEmail(String para, String titulo, String conteudo) {
        String MeuEmail = System.getenv("EMAIL");  // Agora utilizando variável de ambiente
        String MinhaSenha = System.getenv("SENHA_EMAIL");

        SimpleEmail email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator(MeuEmail, MinhaSenha));
        email.setSSLOnConnect(true);
        email.setStartTLSEnabled(true);  // Adiciona suporte TLS

        try {
            email.setFrom(MeuEmail);
            email.setSubject(titulo);
            email.setMsg(conteudo);
            email.addTo(para);
            email.send();
            System.out.println("E-mail enviado com sucesso");
        } catch (Exception e) {
            System.err.println("Erro ao enviar e-mail: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void enviarClicked() {
        try {
            helloController.conection();
            String query = "SELECT senha FROM Cliente WHERE nome = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, usuarioText.getText());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String senha = rs.getString("senha");
                String para = emailText.getText();
                enviarSenhaPorEmail(para, "Recuperar Senha", "Sua senha é: " + senha);
                showAlert("Sucesso", "Senha enviada ao e-mail.", Alert.AlertType.ERROR);
            } else {
                showAlert("Erro", "Usuário não encontrado.", Alert.AlertType.ERROR);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            showAlert("Erro", "Erro ao acessar o banco de dados: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        } finally {
            try {
                helloController.closeConection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void voltarClicked(ActionEvent event) {
        helloApplication.loadScreen("paginaLogin.fxml", "Vk", event);
    }
}
