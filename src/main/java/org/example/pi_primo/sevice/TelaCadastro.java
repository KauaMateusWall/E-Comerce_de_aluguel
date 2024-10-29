package org.example.pi_primo.sevice;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.pi_primo.HelloApplication;
import org.example.pi_primo.HelloController;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.example.pi_primo.HelloController.conn;
import static org.example.pi_primo.HelloController.showAlert;

public class TelaCadastro {

    HelloApplication helloApplication = new HelloApplication();
    HelloController helloController = new HelloController();


    @FXML
    public CheckBox CheckBoxcadastro;
    @FXML
    public PasswordField CadastroSenha;
    @FXML
    public javafx.scene.control.TextField CadastroEndereco;
    @FXML
    public javafx.scene.control.TextField CadastroEmail;
    @FXML
    public javafx.scene.control.TextField CadastroCpf;
    @FXML
    public javafx.scene.control.TextField CadastroUsuario;
    @FXML
    public DatePicker Data_Nascimento;
    @FXML
    public TextField CadastroTelefone;



    @FXML
    public void voltarTelaLogin(ActionEvent event) {
        helloApplication.loadScreen("paginaLogin.fxml", "VK", event);
    }

    @FXML
    public void Cadastrar(ActionEvent event) throws SQLException {
        try {
            if (CheckBoxcadastro.isSelected()) {
                helloController.conection();
                String InserSQL = "INSERT INTO Cliente (nome, senha, cpf, Data_Nascimento, endereco, email, telefone) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement InsertSMT = conn.prepareStatement(InserSQL)) {
                    String nome = CadastroUsuario.getText();
                    String cpf = CadastroCpf.getText();
                    String senha = CadastroSenha.getText();
                    String email = CadastroEmail.getText();
                    String telefone = CadastroTelefone.getText();
                    String endereco = CadastroEndereco.getText();
                    LocalDate Data_Nacsimento = Data_Nascimento.getValue();

                    if (nome.isEmpty() || cpf.isEmpty() || senha.isEmpty() || email.isEmpty() || telefone.isEmpty() || endereco.isEmpty() || Data_Nacsimento == null) {
                        showAlert("Campos vazios", "Por favor, preencha todos os campos.");
                        return;
                    }

                    InsertSMT.setString(1, nome);
                    InsertSMT.setString(2, senha);
                    InsertSMT.setString(3, cpf);
                    InsertSMT.setDate(4, Date.valueOf(Data_Nacsimento));
                    InsertSMT.setString(5, endereco);
                    InsertSMT.setString(6, email);
                    InsertSMT.setString(7, telefone);

                    InsertSMT.execute();
                    showAlert("Cadastro bem-sucedido", "Cadastro de " + nome + " foi um sucesso!!!");
                    helloApplication.loadScreen("paginalogin.fxml", "VK", event);
                }
            } else {
                showAlert("Atenção", "Por favor, aceite os termos de cadastro.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            helloController.closeConection();
        }
    }

    public void TermosTelaCadastro(ActionEvent event) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://docs.google.com/document/d/1r3z3w2721rs7Jqg07W7eNpVwm8qyVrQ9KSICxcLpeag/edit?usp=sharing"));
    }




}
