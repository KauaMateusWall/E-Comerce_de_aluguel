package org.example.pi_primo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class HelloController {

    private static final String URL = "jdbc:mysql://localhost:3306/PI_Primo";
    private static final String USER = "root";
    private static final String PASSWORD = "0000";
    public static Connection conn;

    public static void conection() throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void closeConection() throws SQLException {
        conn.close();
    }

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
    public void onEntrarClicked() throws SQLException {
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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("paginaCadastro.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 640, 400);

            stage.setTitle("Empréstimo VK - Cadastro");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void voltarTelaLogin(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("paginaLogin.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 640, 400);

            stage.setTitle("Empréstimo VK - Cadastro");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void Cadastrar() throws SQLException {
        try {
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
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConection();
        }
    }
}
