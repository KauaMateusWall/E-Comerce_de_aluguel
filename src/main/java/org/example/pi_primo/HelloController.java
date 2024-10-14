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
import javafx.event.ActionEvent;  // Certifique-se de que está usando javafx.event.ActionEvent
import org.example.pi_primo.sevice.CadastroDAO;

import java.io.IOException;
import java.sql.SQLException;

public class HelloController {

    @FXML
    public static PasswordField CadastroSenha;
    @FXML
    public static TextField CadastroEndereco;
    @FXML
    public static TextField CadastroEmail;
    @FXML
    public static TextField CadastroCpf;
    @FXML
    public static TextField CadastroUsuario;
    @FXML
    public static DatePicker Data_Nacimento;
    @FXML
    public static TextField CadastroTelefone;
    @FXML
    private TextField UsuarioTXT;

    @FXML
    private PasswordField SenhaTXT;

    @FXML
    public void onEntrarClicked() {
        String usuario = UsuarioTXT.getText();
        String senha = SenhaTXT.getText();

        if (usuario.equals(UsuarioTXT) && senha.equals(SenhaTXT)) {
            showAlert("Login bem-sucedido", "Bem-vindo, " + usuario + "!");
        } else {
            showAlert("Falha no login", "Nome de usuário ou senha incorretos.");
        }
    }

    private void showAlert(String titulo, String mensagem) {
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
        CadastroDAO cadastroDAO = new CadastroDAO();

        cadastroDAO.Cadastrar(CadastroUsuario,CadastroCpf,CadastroEmail,CadastroTelefone,CadastroEndereco,Data_Nacimento);
    }
}
