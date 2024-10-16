package org.example.pi_primo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.example.pi_primo.HelloController.*;

public class TelaCadastroProduto {
    public Button cadastrarButton;
    public Button voltarButton;
    public TextField tipoText;
    public TextField precoText;
    public TextField nomeText;
    public TextArea descricaoText;

    public void voltarClicked(ActionEvent event) {
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

    public void cadastrarClicked(ActionEvent actionEvent) throws SQLException {
        HelloController helloController = new HelloController();
        try {
            helloController.conection();
            String InserSQL = "INSERT INTO Produto (nome, descricao, categoria_Produto, preco) VALUES (?, ?, ?, ?)";
            try (PreparedStatement InsertSMT = conn.prepareStatement(InserSQL)) {
                String nome = nomeText.getText();
                String preco = precoText.getText();
                String descricao = descricaoText.getText();


                if (nome.isEmpty() || preco.isEmpty() || descricao == null) {
                    showAlert("Campos vazios", "Por favor, preencha todos os campos.");
                    return;
                }

                InsertSMT.setString(1, nome);
                InsertSMT.setString(2, preco);
                InsertSMT.setString(3, descricao);

                InsertSMT.execute();
                showAlert("Cadastro bem-sucedido", "Cadastro de " + nome + " foi um sucesso!!!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            helloController.closeConection();
        }


    }
}
