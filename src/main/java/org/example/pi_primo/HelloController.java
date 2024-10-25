package org.example.pi_primo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

    public  void conection() throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void closeConection() throws SQLException {
        conn.close();
    }

    HelloAplication helloAplication = new HelloAplication();

    @FXML
    public CheckBox CheckBoxcadastro;
    @FXML
    public PasswordField CadastroSenha;
    @FXML
    public TextField CadastroEndereco;
    @FXML
    public TextField CadastroEmail;
    @FXML
    public TextField CadastroCpf;
    @FXML
    public TextField CadastroUsuario;
    @FXML
    public DatePicker Data_Nascimento;
    @FXML
    public TextField CadastroTelefone;
    @FXML
    private TextField UsuarioTXT;
    @FXML
    private PasswordField SenhaTXT;


    @FXML
    public void onEntrarClicked(ActionEvent event) throws SQLException {
        String usuario = UsuarioTXT.getText();
        String senha = SenhaTXT.getText();

        try {
            conection();

            String query = "SELECT * FROM Cliente WHERE nome = ? AND senha = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                helloAplication.loadScreen("paginaMenu.fxml","VK",event);

                showAlert("Login bem-sucedido", "Bem-vindo, " + usuario + "!");

            } else {
                showAlert("Falha no login", "Nome de usuário ou senha incorretos.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConection();
        }
    }


    public static void showAlert(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    public void omCadastroClicked(ActionEvent event) {
        helloAplication.loadScreen("paginaCadastro.fxml","VK",event);
    }

    @FXML
    public void voltarTelaLogin(ActionEvent event) {
        helloAplication.loadScreen("paginaLogin.fxml","VK",event);
    }

    @FXML
    public void Cadastrar(ActionEvent event) throws SQLException {
        try {
            if (CheckBoxcadastro.isSelected()) {
                conection();
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
                    helloAplication.loadScreen("paginalogin.fxml", "VK",event);
                }
            } else {
                showAlert("Atenção", "Por favor, aceite os termos de cadastro.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConection();
        }
    }

    public void TermosTelaCadastro(ActionEvent event) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://docs.google.com/document/d/1r3z3w2721rs7Jqg07W7eNpVwm8qyVrQ9KSICxcLpeag/edit?usp=sharing"));
    }

}
